// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Etablissement {
    private final List<Client> clients;
    private final RendezVous[][] planning;

    public Etablissement(String nom) {
        this.clients = new ArrayList<>();
        this.planning = new RendezVous[16][7]; // 16 créneaux (10h-18h par 30 min) sur 7 jours
    }

    // Méthode pour ajouter un client
    public Client ajouterClient(String nom, String numeroTelephone) {
        return ajouterClient(nom, numeroTelephone, null);
    }

    public Client ajouterClient(String nom, String numeroTelephone, String email) {
        Client client = new Client(nom, numeroTelephone, email);
        clients.add(client);
        return client;
    }

    // Méthode de recherche de client
    public Client rechercherClient(String nom, String numeroTelephone) {
        return clients.stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nom) && c.getNumeroTelephone().equals(numeroTelephone))
                .findFirst()
                .orElse(null);
    }

    // Ajouter un rendez-vous
    public RendezVous ajouterRendezVous(Client client, Prestation prestation, LocalDateTime horaire) {
        int heureIndex = (horaire.getHour() - 10) * 2 + (horaire.getMinute() / 30);
        int jourIndex = horaire.getDayOfWeek().getValue() - 1; // Lundi = 0
        if (planning[heureIndex][jourIndex] != null) {
            System.out.println("Créneau déjà occupé !");
            return null;
        }
        RendezVous rdv = new RendezVous(client, prestation, horaire);
        planning[heureIndex][jourIndex] = rdv;
        return rdv;
    }

    // Afficher planning pour un jour donné
    public void afficherPlanningPourJour(int jourIndex) {
        System.out.println("Planning pour le jour " + (jourIndex + 1));
        for (int i = 0; i < planning.length; i++) {
            String horaire = (10 + i / 2) + ":" + (i % 2 == 0 ? "00" : "30");
            if (planning[i][jourIndex] != null) {
                System.out.println(horaire + " - " + planning[i][jourIndex]);
            } else {
                System.out.println(horaire + " - Libre");
            }
        }
    }

    // Sauvegarde des clients dans un fichier
    public void sauvegarderClients(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Client client : clients) {
                writer.write(client.versFichier());
                writer.newLine();
            }
        }
    }

    // Chargement des clients à partir d'un fichier
    public void chargerClients(String cheminFichier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split(" : ");
                String nom = parties[1];
                String numero = parties[2];
                String email = (parties.length > 3) ? parties[3] : null;
                ajouterClient(nom, numero, email);
            }
        }
    }

    // Sauvegarde des rendez-vous
    public void sauvegarderRendezVous(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (int jour = 0; jour < planning[0].length; jour++) {
                for (RendezVous[] planning1 : planning) {
                    if (planning1[jour] != null) {
                        writer.write(planning1[jour].versFichier());
                        writer.newLine();
                    }
                }
            }
        }
    }

    // Chargement des rendez-vous
    public void chargerRendezVous(String cheminFichier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split("\n");
                LocalDateTime horaire = LocalDateTime.parse(parties[0]);
                int numeroClient = Integer.parseInt(parties[1]);
                char categorie = parties[2].charAt(0);
                Client client = clients.stream().filter(c -> c.getNumeroClient() == numeroClient).findFirst().orElse(null);
                if (client != null) {
                    Prestation prestation = switch (categorie) {
                        case 'A' -> new PrestationExpress(categorie, true);
                        case 'B' -> new PrestationSale(categorie);
                        default -> null;
                    };
                    ajouterRendezVous(client, prestation, horaire);
                }
            }
        }
    }
}

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
    // Liste des clients enregistrés dans l'établissement
    private final List<Client> clients;
    // Planning de l'établissement, avec 16 créneaux horaires (10h-18h par tranches de 30 min) sur 7 jours (mardi-dimanche)
    private final RendezVous[][] planning;
    
    public int getNombreDeClients() {
    return this.clients.size(); // Suppose que `clients` est une liste contenant les clients
}

    // Constructeur de l'établissement qui initialise la liste des clients et le planning
    public Etablissement(String nom) {
        this.clients = new ArrayList<>();
        this.planning = new RendezVous[16][7]; // 16 créneaux horaires et 7 jours
    }

    // === Gestion des Clients ===

    // Ajouter un nouveau client avec nom, numéro de téléphone et email
    public Client ajouterClient(String nom, String numeroTelephone, String email) {
        Client client = new Client(nom, numeroTelephone, email);
        clients.add(client);
        trierClients(); // Trier les clients par ordre alphabétique
        return client;
    }

    // Ajouter un client sans email (surcharge de la méthode précédente)
    public Client ajouterClient(String nom, String numeroTelephone) {
        return ajouterClient(nom, numeroTelephone, null);
    }

    // Rechercher un client par son nom et son numéro de téléphone
    public Client rechercherClient(String nom, String numeroTelephone) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nom) && client.getNumeroTelephone().equals(numeroTelephone)) {
                return client;
            }
        }
        return null; // Retourne null si le client n'est pas trouvé
    }

    // Méthode privée pour trier les clients par ordre alphabétique sur le nom
    private void trierClients() {
        clients.sort((c1, c2) -> c1.getNom().compareToIgnoreCase(c2.getNom()));
    }

    // Afficher la liste de tous les clients
    public void afficherClients() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    // === Gestion des Rendez-Vous ===

    // Ajouter un rendez-vous pour un client, une prestation et un horaire spécifique
    public RendezVous ajouterRendezVous(Client client, Prestation prestation, LocalDateTime horaire) {
        // Vérifier si l'horaire est un lundi (1) - l'établissement est fermé ce jour-là
        if (horaire.getDayOfWeek().getValue() == 1) {
            System.out.println("L'etablissement est ferme le lundi !");
            return null;
        }

        // Vérifier si l'horaire est dans le passé
        if (horaire.isBefore(LocalDateTime.now())) {
            System.out.println("Impossible de choisir une date passee.");
            return null;
        }

        // Vérifier si l'horaire est dans la plage horaire valide (10h00-18h00, par tranches de 30 minutes)
        int heure = horaire.getHour();
        int minute = horaire.getMinute();
        if (heure < 10 || heure >= 18 || (minute != 0 && minute != 30)) {
            System.out.println("Les horaires doivent etre entre 10h00 et 18h00, par tranches de 30 minutes.");
            return null;
        }

        // Calculer les indices pour l'heure et le jour du planning
        int heureIndex = (horaire.getHour() - 10) * 2 + (horaire.getMinute() / 30);
        int jourIndex = horaire.getDayOfWeek().getValue() - 1;

        // Vérifier si le créneau est déjà occupé
        if (planning[heureIndex][jourIndex] != null) {
            System.out.println("Creneau deja occupe !");
            return null;
        }
        // Verifier si l'horaire est dans les 7 prochains jours
        LocalDateTime dateLimite = LocalDateTime.now().plusDays(7);
        if (horaire.isAfter(dateLimite)) {
            System.out.println("Les rendez-vous doivent etre pris dans les 7 prochains jours maximum.");
            return null;
        }

        // Créer le rendez-vous et l'ajouter au planning
        RendezVous rdv = new RendezVous(client, prestation, horaire);
        planning[heureIndex][jourIndex] = rdv;
        return rdv;
    }

    // Afficher le planning pour un jour spécifique
    public void afficherPlanningPourJour(int jourIndex) {
        if (jourIndex == 0) {
            System.out.println("L'etablissement est ferme le lundi.");
            return;
        }

        System.out.println("Planning pour le jour " + (jourIndex + 1));
        // Parcourir les créneaux horaires pour afficher leur disponibilité
        for (int i = 0; i < planning.length; i++) {
            String horaire = (10 + i / 2) + ":" + (i % 2 == 0 ? "00" : "30");
            if (planning[i][jourIndex] != null) {
                System.out.println(horaire + " - " + planning[i][jourIndex]);
            } else {
                System.out.println(horaire + " - Libre");
            }
        }
    }

    // === Sauvegarde et Chargement des Données ===

    // Sauvegarder les clients dans un fichier
    public void sauvegarderClients(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Client client : clients) {
                writer.write(client.versFichier());
                writer.newLine();
            }
        }
    }

    // Charger les clients depuis un fichier
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

    // Sauvegarder les rendez-vous dans un fichier
    public void sauvegarderRendezVous(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (int jour = 0; jour < 7; jour++) {
                for (int creneau = 0; creneau < 16; creneau++) {
                    if (planning[creneau][jour] != null) {
                        writer.write(planning[creneau][jour].versFichier());
                        writer.newLine();
                    }
                }
            }
        }
    }

    // Charger les rendez-vous depuis un fichier
    public void chargerRendezVous(String cheminFichier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parties = ligne.split("\n");
                LocalDateTime horaire = LocalDateTime.parse(parties[0]);
                int numeroClient = Integer.parseInt(parties[1]);
                char categorie = parties[2].charAt(0);

                // Recherche du client à partir du numéro
                Client client = clients.stream()
                        .filter(c -> c.getNumeroClient() == numeroClient)
                        .findFirst()
                        .orElse(null);

                if (client != null) {
                    // Création de la prestation en fonction de la catégorie
                    Prestation prestation = switch (categorie) {
                        case 'A' -> new PrestationExpress(categorie, true);
                        case 'B' -> new PrestationSale(categorie);
                        case 'C' -> new PrestationTresSale(categorie, 1); // Exemple : salissure 1
                        default -> null;
                    };
                    // Ajouter le rendez-vous au planning
                    ajouterRendezVous(client, prestation, horaire);
                }
            }
        }
    }
}

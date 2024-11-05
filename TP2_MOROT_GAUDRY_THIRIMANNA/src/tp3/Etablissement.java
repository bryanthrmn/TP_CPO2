package tp3;

import java.util.ArrayList;
import java.util.List;

public class Etablissement {
    private String nom;
    private List<Client> clients;
    private List<RendezVous> planning; // Liste de rendez-vous pour les 7 jours suivants

    public Etablissement(String nom) {
        this.nom = nom;
        this.clients = new ArrayList<>();
        this.planning = new ArrayList<>();
    }

    public Client ajouterClient(String nom, String telephone) {
        Client client = new Client(nom, telephone);
        clients.add(client);
        return client;
    }

    public Client ajouterClient(String nom, String telephone, String email) {
        Client client = new Client(nom, telephone, email);
        clients.add(client);
        return client;
    }

    public Client rechercherClient(String nom, String telephone) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nom) && client.getTelephone().equals(telephone)) {
                return client;
            }
        }
        return null;
    }

    public void ajouterRendezVous(RendezVous rdv) {
        planning.add(rdv);
    }

    public void afficherPlanning() {
        for (RendezVous rdv : planning) {
            System.out.println(rdv);
        }
    }
}

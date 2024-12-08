// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

import java.time.LocalDateTime;

public class RendezVous {
    // Attributs 
    private final Client client; // Le client associé au rdv
    private final Prestation prestation; // La prestation demandée pour ce rendez-vous
    private final LocalDateTime horaire; // L'heure du rendez-vous

    // Constructeur 
    public RendezVous(Client client, Prestation prestation, LocalDateTime horaire) {
        this.client = client;
        this.prestation = prestation;
        this.horaire = horaire;
    }

    // Informations du rendez-vous sous forme de chaîne
    @Override
    public String toString() {
        // Retourne une chaîne avec l'horaire, les informations du client, la prestation,
        // et le prix calculé de la prestation en euros
        return horaire + " - " + client + " - " + prestation + " - Prix: " + prestation.calculerPrix() + "EUR";
    }

    // Méthode pour obtenir les informations du rendez-vous sous forme de chaîne, prêt à être sauvegardé dans un fichier
    public String versFichier() {
        // Retourne une chaîne formatée avec l'horaire, le numéro du client et les informations sur la prestation avec son prix
        return horaire + "\n" + client.getNumeroClient() + "\n" + prestation + " : " + prestation.calculerPrix();
    }
}

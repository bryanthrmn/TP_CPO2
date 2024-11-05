package tp3;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Création de l'établissement
        Etablissement etablissement = new Etablissement("Station de Nettoyage");

        // Ajout d'un client
        Client client1 = etablissement.ajouterClient("Dupont", "0123456789");

        // Création d'une prestation express
        PrestationExpress prestationExpress = new PrestationExpress('A', true);
        System.out.println("Prix Prestation Express: " + prestationExpress.calculerPrixTotal() + " €");

        // Création d'une prestation pour un véhicule sale
        PrestationSale prestationSale = new PrestationSale('B');
        System.out.println("Prix Prestation Sale: " + prestationSale.calculerPrixTotal() + " €");

        // Création d'une prestation pour un véhicule très sale
        PrestationTresSale prestationTresSale = new PrestationTresSale('C', 2); // Taches de boue
        System.out.println("Prix Prestation Très Sale: " + prestationTresSale.calculerPrixTotal() + " €");

        // Création et ajout de rendez-vous
        RendezVous rdv1 = new RendezVous(client1, prestationExpress, LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
        etablissement.ajouterRendezVous(rdv1);

        // Affichage du planning de rendez-vous
        etablissement.afficherPlanning();
    }
}

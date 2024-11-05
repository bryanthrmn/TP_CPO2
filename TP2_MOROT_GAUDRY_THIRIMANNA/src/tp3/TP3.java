package tp3;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Etablissement etablissement = new Etablissement("Station de Nettoyage");

        // Ajouter un client
        Client client1 = etablissement.ajouterClient("Dupont", "0123456789");

        // Ajouter une prestation express
        PrestationExpress prestationExpress = new PrestationExpress('A', true);
        RendezVous rdv1 = new RendezVous(client1, prestationExpress, LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
        
        etablissement.ajouterRendezVous(rdv1);
        etablissement.afficherPlanning();
    }
}

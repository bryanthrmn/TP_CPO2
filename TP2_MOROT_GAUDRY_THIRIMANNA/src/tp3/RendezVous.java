package tp3;

import java.time.LocalDateTime;

public class RendezVous {
    private Client client;
    private Prestation prestation;
    private LocalDateTime dateHeure;
    private double prix;

    public RendezVous(Client client, Prestation prestation, LocalDateTime dateHeure) {
        this.client = client;
        this.prestation = prestation;
        this.dateHeure = dateHeure;
        this.prix = prestation.calculerPrixTotal();
    }

    public String toString() {
        return "Rendez-vous pour " + client + " à " + dateHeure + ", Prestation: " + prestation + ", Prix: " + prix + " €";
    }
}

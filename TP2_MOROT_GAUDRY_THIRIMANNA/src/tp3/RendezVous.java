// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

import java.time.LocalDateTime;

public class RendezVous {
    private final Client client;
    private final Prestation prestation;
    private final LocalDateTime horaire;

    public RendezVous(Client client, Prestation prestation, LocalDateTime horaire) {
        this.client = client;
        this.prestation = prestation;
        this.horaire = horaire;
    }

    @Override
    public String toString() {
        return horaire + " - " + client + " - " + prestation + " - Prix: " + prestation.calculerPrix() + "€";
    }

    public String versFichier() {
        return horaire + "\n" + client.getNumeroClient() + "\n" + prestation + " : " + prestation.calculerPrix();
    }
}

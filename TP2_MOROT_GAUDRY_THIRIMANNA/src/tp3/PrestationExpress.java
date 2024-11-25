// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationExpress extends Prestation {
    private final boolean nettoyageInterieur;

    public PrestationExpress(char categorieVehicule, boolean nettoyageInterieur) {
        super(categorieVehicule);
        this.nettoyageInterieur = nettoyageInterieur;
    }

    @Override
    public double calculerPrix() {
        double prix = calculerLavage() + calculerSechage();
        if (nettoyageInterieur) {
            prix += calculerNettoyageInterieur();
        }
        return prix;
    }

    private double calculerLavage() {
        return switch (categorieVehicule) {
            case 'A' -> 20;
            case 'B' -> 30; // 50% de majoration
            case 'C' -> 35; // 75% de majoration
            default -> 0;
        };
    }

    private double calculerSechage() {
        return switch (categorieVehicule) {
            case 'A' -> 10;
            case 'B' -> 10.5; // 5% de majoration
            case 'C' -> 11; // 10% de majoration
            default -> 0;
        };
    }

    private double calculerNettoyageInterieur() {
        return switch (categorieVehicule) {
            case 'A', 'B' -> 30;
            case 'C' -> 40;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return super.toString() + " - Prestation Express (Interieur: " + nettoyageInterieur + ")";
    }
}

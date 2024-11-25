// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationSale extends Prestation {
    public PrestationSale(char categorieVehicule) {
        super(categorieVehicule);
    }

    @Override
    public double calculerPrix() {
        return calculerPrelavage() + calculerLavage() + calculerSechage();
    }

    private double calculerPrelavage() {
        return switch (categorieVehicule) {
            case 'A' -> 5;
            case 'B' -> 7.5; // 50% de majoration
            case 'C' -> 8.75; // 75% de majoration
            default -> 0;
        };
    }

    private double calculerLavage() {
        return switch (categorieVehicule) {
            case 'A' -> 20;
            case 'B' -> 30;
            case 'C' -> 35;
            default -> 0;
        };
    }

    private double calculerSechage() {
        return switch (categorieVehicule) {
            case 'A' -> 10;
            case 'B' -> 10.5;
            case 'C' -> 11;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return super.toString() + " - Prestation Sale";
    }
}

// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationTresSale extends PrestationSale {
    private final int typeSalissure;

    public PrestationTresSale(char categorieVehicule, int typeSalissure) {
        super(categorieVehicule);
        this.typeSalissure = typeSalissure;
    }

    @Override
    public double calculerPrix() {
        double prix = super.calculerPrix();
        double surcout = switch (typeSalissure) {
            case 1 -> 5; // Nourriture
            case 2 -> 7; // Boue
            case 3 -> 10; // Transpiration
            case 4 -> 12; // Graisse
            default -> 0;
        };
        return prix + surcout;
    }

    @Override
    public String toString() {
        return super.toString() + " - Type de salissure: " + typeSalissure;
    }
}

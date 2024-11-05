package tp3;

public class PrestationTresSale extends PrestationSale {
    private int typeSalissure; // 1 à 4

    public PrestationTresSale(char categorieVehicule, int typeSalissure) {
        super(categorieVehicule);
        this.typeSalissure = typeSalissure;
    }

    @Override
    public double calculerPrixTotal() {
        double prix = super.calculerPrixTotal();
        prix += calculerSurcoutTypeSalissure();
        return prix;
    }

    private double calculerSurcoutTypeSalissure() {
        // Calcul du surcoût selon le type de salissure
    }
}

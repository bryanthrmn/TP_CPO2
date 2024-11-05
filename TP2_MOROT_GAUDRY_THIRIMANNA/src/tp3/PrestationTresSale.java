package tp3;

class PrestationTresSale extends PrestationSale {
    private int typeSalissure;

    public PrestationTresSale(char categorieVehicule, int typeSalissure) {
        super(categorieVehicule);
        this.typeSalissure = typeSalissure;
    }

    @Override
    public double calculerPrixTotal() {
        double surcout = 0;
        switch (typeSalissure) {
            case 1:  // Taches de nourriture
                surcout = 5;
                break;
            case 2:  // Taches de boue
                surcout = 10;
                break;
            case 3:  // Taches de transpiration
                surcout = 8;
                break;
            case 4:  // Taches de graisse
                surcout = 15;
                break;
            default:
                surcout = 0;
        }
        return super.calculerPrixTotal() + surcout;
    }

    @Override
    public void afficherPrestation() {
        super.afficherPrestation();
        System.out.println("Prestation pour véhicule très sale avec type de salissure: " + typeSalissure);
    }
}

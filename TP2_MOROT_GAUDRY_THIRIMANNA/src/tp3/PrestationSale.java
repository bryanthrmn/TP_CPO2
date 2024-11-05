package tp3;

class PrestationSale extends Prestation {
    public PrestationSale(char categorieVehicule) {
        super(categorieVehicule);
    }

    @Override
    public double calculerPrixTotal() {
        return calculerPrixPrelavage() + calculerPrixLavage() + calculerPrixSechage();
    }

    @Override
    public void afficherPrestation() {
        super.afficherPrestation();
        System.out.println("Prestation pour véhicule sale (prélavage, lavage, séchage)");
    }
}

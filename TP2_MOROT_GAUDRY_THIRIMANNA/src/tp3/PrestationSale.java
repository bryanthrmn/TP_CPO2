package tp3;

public class PrestationSale extends Prestation {
    public PrestationSale(char categorieVehicule) {
        super(categorieVehicule);
    }

    @Override
    public double calculerPrixTotal() {
        return calculerPrelavage() + calculerLavage() + calculerSechage();
    }

    private double calculerPrelavage() {
        // Calcul du prix du prélavage selon la catégorie
    }

    private double calculerLavage() {
        // Calcul du prix du lavage selon la catégorie
    }

    private double calculerSechage() {
        // Calcul du prix du séchage selon la catégorie
    }
}

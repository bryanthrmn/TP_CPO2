package tp3;
public class PrestationExpress extends Prestation {
    private boolean nettoyageInterieur;

    public PrestationExpress(char categorieVehicule, boolean nettoyageInterieur) {
        super(categorieVehicule);
        this.nettoyageInterieur = nettoyageInterieur;
    }

    @Override
    public double calculerPrixTotal() {
        double prix = 0;
        prix += calculerLavage();
        prix += calculerSechage();
        if (nettoyageInterieur) {
            prix += calculerNettoyageInterieur();
        }
        return prix;
    }

    private double calculerLavage() {
        // Calcul du prix du lavage selon la catégorie
    }

    private double calculerSechage() {
        // Calcul du prix du séchage selon la catégorie
    }

    private double calculerNettoyageInterieur() {
        // Calcul du prix de nettoyage intérieur
    }
}

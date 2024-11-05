package tp3;

class PrestationExpress extends Prestation {
    private boolean nettoyageInterieur;

    public PrestationExpress(char categorieVehicule, boolean nettoyageInterieur) {
        super(categorieVehicule);
        this.nettoyageInterieur = nettoyageInterieur;
    }

    @Override
    public double calculerPrixTotal() {
        double prix = calculerPrixLavage() + calculerPrixSechage();
        if (nettoyageInterieur) {
            if (categorieVehicule == 'C') {
                prix += 40;  // Prix pour nettoyage intérieur catégorie C
            } else {
                prix += 30;  // Prix pour nettoyage intérieur catégories A et B
            }
        }
        return prix;
    }

    @Override
    public void afficherPrestation() {
        super.afficherPrestation();
        System.out.println("Prestation Express, Nettoyage intérieur: " + (nettoyageInterieur ? "Oui" : "Non"));
    }
}

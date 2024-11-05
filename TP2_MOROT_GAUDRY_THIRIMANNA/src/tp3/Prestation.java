package tp3;
public class Prestation {
    protected char categorieVehicule;  // A, B ou C

    // Constructeur
    public Prestation(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    // Méthode pour calculer le prix du lavage selon la catégorie du véhicule
    public double calculerPrixLavage() {
        double prixBase = 20;
        switch (categorieVehicule) {
            case 'B':
                return prixBase * 1.5;  // Majoré de 50% pour catégorie B
            case 'C':
                return prixBase * 1.75; // Majoré de 75% pour catégorie C
            default:
                return prixBase;        // Prix standard pour catégorie A
        }
    }

    // Méthode pour calculer le prix du séchage selon la catégorie du véhicule
    public double calculerPrixSechage() {
        double prixBase = 10;
        switch (categorieVehicule) {
            case 'B':
                return prixBase * 1.05;  // Majoré de 5% pour catégorie B
            case 'C':
                return prixBase * 1.10;  // Majoré de 10% pour catégorie C
            default:
                return prixBase;         // Prix standard pour catégorie A
        }
    }

    // Méthode pour calculer le prix du prélavage selon la catégorie du véhicule
    public double calculerPrixPrelavage() {
        double prixBase = 5;
        switch (categorieVehicule) {
            case 'B':
                return prixBase * 1.5;  // Majoré de 50% pour catégorie B
            case 'C':
                return prixBase * 1.75; // Majoré de 75% pour catégorie C
            default:
                return prixBase;        // Prix standard pour catégorie A
        }
    }

    // Méthode pour calculer le prix total (à redéfinir dans chaque sous-classe)
    public double calculerPrixTotal() {
        return 0.0;  // Les sous-classes redéfinissent cette méthode
    }

    // Méthode d'affichage de la prestation
    public void afficherPrestation() {
        System.out.println("Prestation pour véhicule de catégorie " + categorieVehicule);
    }
}

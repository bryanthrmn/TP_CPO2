// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationSale extends Prestation {
    public PrestationSale(char categorieVehicule) {
        super(categorieVehicule);  // Appel du constructeur 
    }

    // Calculer le prix total de la prestation sale
    @Override
    public double calculerPrix() {
        // Calcul du prix en fonction des différentes étapes (prélavage, lavage, séchage)
        return calculerPrelavage() + calculerLavage() + calculerSechage();
    }

    // Méthode pour obtenir une représentation textuelle de la prestation sale
    @Override
    public String toString() {
        // Appelle toString() / ajoute l'information de la prestation sale
        return super.toString() + " - Prestation Sale";
    }
}

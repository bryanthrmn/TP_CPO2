// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationExpress extends Prestation {
    // Variable d'instance pour savoir si le nettoyage intérieur est inclus
    private final boolean nettoyageInterieur;

    // Constructeur pour initialiser la catégorie du véhicule et l'option de nettoyage intérieur
    public PrestationExpress(char categorieVehicule, boolean nettoyageInterieur) {
        super(categorieVehicule);  // Appel du constructeur de la classe parent Prestation
        this.nettoyageInterieur = nettoyageInterieur;
    }

    // Méthode pour calculer le prix total de la prestation Express
    @Override
    public double calculerPrix() {
        // Calcul du prix de base pour lavage et séchage
        double prix = calculerLavage() + calculerSechage();

        // Ajout des coûts supplémentaires si nettoyage intérieur est activé
        if (nettoyageInterieur) {
            prix += switch (categorieVehicule) {
                case 'A', 'B' -> 30;  // Nettoyage intérieur pour catégories A et B à 30 EUR
                case 'C' -> 40;       // Nettoyage intérieur pour catégorie C à 40 EUR
                default -> 0;         
            };
        }

        return prix;  // retourne le prix
    }

    // Représentation en texte
    @Override
    public String toString() {
        // Appelle toString() / ajoute l'information du nettoyage intérieur
        return super.toString() + " - Prestation Express (Interieur: " + nettoyageInterieur + ")";
    }
}

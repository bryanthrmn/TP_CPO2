// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public abstract class Prestation {
    protected final char categorieVehicule;

    // Constructeur pour initialiser la catégorie du véhicule
    public Prestation(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    // Calcul du prix de lavage selon le type de véhicule
    protected double calculerLavage() {
        return switch (categorieVehicule) {
            case 'A' -> 20;              // Catégorie A : 20 EUR
            case 'B' -> 20 * 1.5;        // Catégorie B : lavage à 1.5 fois le prix de A
            case 'C' -> 20 * 1.75;       // Catégorie C : lavage à 1.75 fois le prix de A
            default -> 0;                // 0 si inconnu (par défaut)
        };
    }

    // Calculer le prix du séchage en fonction de la catégorie du véhicule
    protected double calculerSechage() {
        return switch (categorieVehicule) {
            case 'A' -> 10;              // Catégorie A : séchage à 10 EUR
            case 'B' -> 10 * 1.05;       // Catégorie B : coef 1.05
            case 'C' -> 10 * 1.10;       // Catégorie C : coef 1.1
            default -> 0;               
        };
    }

    // Calculer le prix du pré-lavage en fonction de la catégorie du véhicule
    protected double calculerPrelavage() {
        return switch (categorieVehicule) {
            case 'A' -> 5;               // Catégorie A : pré-lavage à 5 EUR
            case 'B' -> 5 * 1.5;         // Catégorie B : coef 1.5
            case 'C' -> 5 * 1.75;        // Catégorie C : coef 1.1
            default -> 0;                
        };
    }

    // Calculer le prix total de la prestation (à définir dans les sous-classes)
    public abstract double calculerPrix();

    // Représentation de texte de la prestation
    @Override
    public String toString() {
        return "Categorie: " + categorieVehicule; // Retourne la catégorie du véhicule
    }
}

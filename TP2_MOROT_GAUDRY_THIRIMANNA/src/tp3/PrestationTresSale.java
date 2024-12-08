// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class PrestationTresSale extends PrestationSale {
    // Attribut
    private final int typeSalissure;

    public PrestationTresSale(char categorieVehicule, int typeSalissure) {
        super(categorieVehicule);  // Appel du constructeur 
        this.typeSalissure = typeSalissure;  // Initialisation du type de salissure
    }

    // Calculer le prix total de la prestation très sale
    @Override
    public double calculerPrix() {
        // Calcul du prix de base (prélavage, lavage, séchage)
        double prixBase = calculerPrelavage() + calculerLavage() + calculerSechage();
        
        // Calcul du surcoût en fonction du type de salissure
        double surcout = switch (typeSalissure) {
            case 1 -> 5;   
            case 2 -> 10; 
            case 3 -> 15;  
            case 4 -> 20;  // Plus il y a de salissure, plus le prix est de surcoût est élevé
            default -> 0;  
        };
        
        // Retourne le prix total en ajoutant le surcoût au prix de base
        return prixBase + surcout;
    }

    // Méthode pour obtenir une représentation textuelle de la prestation très sale
    @Override
    public String toString() {
        // Appelle toString() / remplace "Prestation Sale" par "Prestation Très Sale"
        // Ajoute le type de salissure
        return super.toString().replace("Prestation Sale", "Prestation Tres Sale") + " - Type de salissure: " + typeSalissure;
    }
}

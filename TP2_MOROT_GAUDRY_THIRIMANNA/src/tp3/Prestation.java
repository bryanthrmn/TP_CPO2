// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public abstract class Prestation {
    protected char categorieVehicule; // A, B ou C

    public Prestation(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    public abstract double calculerPrix();

    @Override
    public String toString() {
        return "Categorie: " + categorieVehicule;
    }
}

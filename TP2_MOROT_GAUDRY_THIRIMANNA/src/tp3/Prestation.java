package tp3;
public abstract class Prestation {
    protected char categorieVehicule;  // A, B ou C

    public Prestation(char categorieVehicule) {
        this.categorieVehicule = categorieVehicule;
    }

    public abstract double calculerPrixTotal();
}

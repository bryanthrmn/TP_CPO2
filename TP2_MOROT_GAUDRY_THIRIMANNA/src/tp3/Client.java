// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class Client {
    private static int compteur = 1; // Pour générer des numéros de client
    private int numeroClient;
    private String nom;
    private String numeroTelephone;
    private String email;

    // Constructeurs
    public Client(String nom, String numeroTelephone) {
        this.numeroClient = compteur++;
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.email = null;
    }

    public Client(String nom, String numeroTelephone, String email) {
        this(nom, numeroTelephone);
        this.email = email;
    }

    // Méthode toString
    @Override
    public String toString() {
        return numeroClient + " : " + nom + " : " + numeroTelephone + (email != null ? " : " + email : "");
    }

    // Méthode versFichier
    public String versFichier() {
        return numeroClient + " : " + nom + " : " + numeroTelephone + (email != null ? " : " + email : "");
    }

    // Méthode pour comparer deux clients pour le tri
    public boolean placerApres(Client autre) {
        return this.nom.compareToIgnoreCase(autre.nom) > 0;
    }

    // Getters
    public int getNumeroClient() {
        return numeroClient;
    }

    public String getNom() {
        return nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }
}

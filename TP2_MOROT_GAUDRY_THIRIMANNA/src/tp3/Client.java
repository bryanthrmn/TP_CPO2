// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

public class Client {
    // Génère automatiquement les numéros de clients
    private static int compteur = 1;
    private final int numeroClient; // Numéro du client
    private final String nom; // Nom du client
    private final String numeroTelephone; // tél du client
    private final String email; // mail du client, optionnel

    // Constructeur pour un client sans email
    public Client(String nom, String numeroTelephone) {
        // Le numéro du client est automatiquement généré à partir du compteur
        this.numeroClient = compteur++;
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.email = null; // Pas d'email pour ce client
    }

    // Constructeur pour un client avec email
    public Client(String nom, String numeroTelephone, String email) {
        // Le numéro du client est automatiquement généré à partir du compteur
        this.numeroClient = compteur++;
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }

    // Méthode toString pour afficher les informations du client sous forme de chaîne
    @Override
    public String toString() {
        // Si l'email est présent, il est inclus dans l'affichage, sinon il est omis
        return numeroClient + " : " + nom + " : " + numeroTelephone + (email != null ? " : " + email : "");
    }

    // Méthode pour obtenir les informations du client sous forme de chaîne
    public String versFichier() {
        // Appelle la méthode toString pour obtenir la représentation sous forme de chaîne
        return toString();
    }

    // Méthode pour comparer deux clients en fonction de leur nom (ordre alphabétique)
    public boolean placerApres(Client autre) {
        // Compare les noms des deux clients, retourne true si le nom de ce client
        // est après celui de l'autre client dans l'ordre alphabétique
        return this.nom.compareToIgnoreCase(autre.nom) > 0;
    }

    // Méthodes d'accès pour obtenir les attributs du client
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

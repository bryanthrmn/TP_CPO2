package tp3;


  public class Client {
    private static int clientCounter = 0;
    private int clientId;
    private String nom;
    private String telephone;
    private String email;

    // Constructeur sans adresse email
    public Client(String nom, String telephone) {
        this.clientId = ++clientCounter;
        this.nom = nom;
        this.telephone = telephone;
    }

    // Constructeur avec adresse email
    public Client(String nom, String telephone, String email) {
        this(nom, telephone);
        this.email = email;
    }

    public String toString() {
        return "Client ID: " + clientId + ", Nom: " + nom + ", Téléphone: " + telephone + (email != null ? ", Email: " + email : "");
    }

    public boolean placerApres(Client autre) {
        return this.nom.compareToIgnoreCase(autre.nom) > 0;
    }

    public int getClientId() {
        return clientId;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }
}

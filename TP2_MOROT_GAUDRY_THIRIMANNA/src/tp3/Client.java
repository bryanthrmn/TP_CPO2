package tp3;

public class Client {
   //Attributs
    private int numero;
    private String nom, tel, email;
    
    //Constructeurs
    public Client(int numero, String nom, String tel){
        this.numero = numero;
        this.nom = nom;
        this.tel = tel;
        this.email = null;
    }
    
    public Client(int numero, String nom, String tel, String email){
        this.numero = numero;
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }
    
    @Override
    public String toString(){
    return "numero : " + numero + " "
           + "nom : " + nom + " "
           + "tel : " + tel + " "
           + "email : " + email + " ";
    }
}

// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TP3 {
    public static void main(String[] args) {
        Etablissement etablissement = new Etablissement("Station de Nettoyage");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==============Bienvenue dans la station de lavage===============");
            System.out.println("[1] Ajouter un client");
            System.out.println("[2] Ajouter un rendez-vous");
            System.out.println("[3] Afficher le planning d'un jour");
            System.out.println("[4] Sauvegarder les donnees");
            System.out.println("[5] Charger les donnees");
            System.out.println("[6] Quitter");
            System.out.println(" ");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Telephone: ");
                    String telephone = scanner.nextLine();
                    System.out.print("Email (optionnel): ");
                    String email = scanner.nextLine();
                    Client client = email.isEmpty() ?
                            etablissement.ajouterClient(nom, telephone) :
                            etablissement.ajouterClient(nom, telephone, email);
                    System.out.println("Client ajoute: " + client);
                    System.out.println(" ");
                }
                case 2 -> {
                    System.out.print("Nom du client: ");
                    String nom = scanner.nextLine();
                    System.out.print("Telephone: ");
                    String telephone = scanner.nextLine();
                    Client client = etablissement.rechercherClient(nom, telephone);
                    if (client == null) {
                        System.out.println("Client introuvable !");
                        break;
                    }
                    System.out.print("Categorie du vehicule (A, B, C): ");
                    char categorie = scanner.nextLine().charAt(0);
                    System.out.print("Type de prestation (1: Express, 2: Sale, 3: Tres Sale): ");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    Prestation prestation = switch (type) {
                        case 1 -> new PrestationExpress(categorie, true);
                        case 2 -> new PrestationSale(categorie);
                        case 3 -> {
                            System.out.print("Type de salissure (1 - 4): ");
                            int salissure = scanner.nextInt();
                            scanner.nextLine();
                            yield new PrestationTresSale(categorie, salissure);
                        }
                        default -> null;
                    };
                    System.out.print("Date et heure du rendez-vous (yyyy-MM-dd HH:mm): ");
                    LocalDateTime horaire = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    RendezVous rdv = etablissement.ajouterRendezVous(client, prestation, horaire);
                    System.out.println("Rendez-vous ajoute: " + rdv);
                    System.out.println(" ");
                }
                case 3 -> {
                    System.out.print("Jour (0-6) : 0 = Lundi, 6 = Dimanche): ");
                    int jour = scanner.nextInt();
                    etablissement.afficherPlanningPourJour(jour);
                    System.out.println(" ");
                }
                case 4 -> {
                    try {
                        etablissement.sauvegarderClients("clients.txt");
                        etablissement.sauvegarderRendezVous("rendezvous.txt");
                        System.out.println("Donnees sauvegardees.");
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
                    }
                    System.out.println(" ");
                }
                case 5 -> {
                    try {
                        etablissement.chargerClients("clients.txt");
                        etablissement.chargerRendezVous("rendezvous.txt");
                        System.out.println("Donnees chargees.");
                    } catch (IOException e) {
                        System.err.println("Erreur lors du chargement: " + e.getMessage());
                    }
                    System.out.println(" ");
                }
                case 6 -> {
                    System.out.println("A bientot !");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Choix invalide ! Veuillez reessayer");
                
            }
        }
    }
}

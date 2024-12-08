// Bryan THIRIMANNA & Thomas MOROT-GAUDRY
// 2A - Semestre 3
// Projet 1 Java
// Novembre - Décembre 2024

package tp3;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TP3 {
    public static void main(String[] args) {
        // Initialisation de l'objet Etablissement avec le nom de la station
        Etablissement etablissement = new Etablissement("Station de Nettoyage");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menu principal
            System.out.println(" ");
            System.out.println("=============== Station de Nettoyage ===============");
            System.out.println("[1] Ajouter un client");
            System.out.println("[2] Planifier un rendez-vous");
            System.out.println("[3] Afficher le planning d'une date");
            System.out.println("[4] Sauvegarder les donnees");
            System.out.println("[5] Charger les donnees");
            System.out.println("[6] A propos du programme");
            System.out.println("[7] Quitter");
            System.out.println(" ");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            // Choix possibles dans le menu
            switch (choix) {
                case "1" -> {
                if (etablissement.getNombreDeClients() >= 50) {
                    System.out.println("Erreur : Limite de stockage de clients atteinte (50+ clients)");
                    break; // Retourne au menu principal
                }                    
            // Demande d'infos sur le client
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Telephone : ");
                    String telephone = scanner.nextLine();
                    System.out.print("Email (optionnel) : ");
                    String email = scanner.nextLine();

                    // Ajout du client en fonction de la présence ou non de l'email
                    Client client = email.isEmpty()
                            ? etablissement.ajouterClient(nom, telephone)
                            : etablissement.ajouterClient(nom, telephone, email);
                    System.out.println("Client ajoute : " + client);
                }
                case "2" -> {
                    // Demande infos du client pour planifier un rdv
                    System.out.print("Nom du client : ");
                    String nom = scanner.nextLine();
                    System.out.print("Telephone : ");
                    String telephone = scanner.nextLine();
                    Client client = etablissement.rechercherClient(nom, telephone);
                    if (client == null) {
                        System.out.println("Client introuvable !");
                        break;
                    }

                    // Choix de catégorie du véhicule
                    char categorie = 0;
                    while (true) {
                        System.out.println("\n=============== Categorie du vehicule ===============");
                        System.out.println("[A] : Berlines");
                        System.out.println("[B] : Citadines");
                        System.out.println("[C] : 4x4");
                        System.out.print("Choix : ");
                        String input = scanner.nextLine().toUpperCase();
                        if (input.matches("[ABC]")) {
                            categorie = input.charAt(0);
                            break;
                        }
                        System.out.println("Choix invalide ! Veuillez reessayer.");
                    }

                    // Choix du type de prestation
                    int type = 0;
                    while (true) {
                        System.out.println("\n=============== Type de prestation ===============");
                        System.out.println("[1] Express");
                        System.out.println("[2] Sale");
                        System.out.println("[3] Tres Sale");
                        System.out.print("Choix : ");
                        String input = scanner.nextLine();
                        try {
                            type = Integer.parseInt(input);
                            if (type >= 1 && type <= 3) break;
                        } catch (NumberFormatException e) {
                            
                        }
                        System.out.println("Choix invalide ! Veuillez reessayer.");
                    }

                    // Création de la prestation en fonction du type choisi
                    Prestation prestation;
                    prestation = switch (type) {
                        case 1 -> {
                            System.out.print("Nettoyer interieur ? (Oui/Non) : ");
                            boolean nettoyageInterieur = false; // défaut
                            OUTER:
                            while (true) {
                                String reponse = scanner.nextLine().trim().toLowerCase();
                                switch (reponse) {
                                    case "oui" -> {
                                        nettoyageInterieur = true;
                                        break OUTER;
                                    }
                                    case "non" -> {
                                        nettoyageInterieur = false;
                                        break OUTER;
                                    }
                                    default -> System.out.println("Reponse invalide ! Veuillez repondre par 'Oui' ou 'Non'.");
                                }
                            }
                            yield new PrestationExpress(categorie, nettoyageInterieur);
                        }

                        case 2 -> new PrestationSale(categorie);
                        case 3 -> {
                            System.out.print("Type de salissure (1-4) : ");
                            int salissure;
                            while (true) {
                                try {
                                    salissure = scanner.nextInt();
                                    scanner.nextLine();
                                    if (salissure >= 1 && salissure <= 4) break;
                                    System.out.println("Valeur invalide. Veuillez entrer un nombre entre 1 et 4.");
                                } catch (InputMismatchException e) {
                                    System.out.println("Entree non valide ! Veuillez reessayer.");
                                    scanner.nextLine(); // vider le buffer
                                }
                            }
                            yield new PrestationTresSale(categorie, salissure);
                        }
                        default -> null;
                    };

                    // demande de la date et de l'heure du rdv
                    System.out.print("Date et heure du rendez-vous (yyyy-MM-dd HH:mm) : ");
                    LocalDateTime horaire;
                    while (true) {
                        try {
                            horaire = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Format de date/heure invalide. Veuillez reessayer (Format attendu : yyyy-MM-dd HH:mm).");
                        }
                    }

                    // ajout du rdv à l'établissement
                    RendezVous rdv = etablissement.ajouterRendezVous(client, prestation, horaire);
                    if (rdv != null) {
                        System.out.println("Rendez-vous ajoute : " + rdv);
                    }
                }

                case "3" -> {
                    // Affichage du planning d'une date spécifique
                    System.out.print("Entrez la date (Format : yyyy-MM-dd) : ");
                    String dateStr = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        // Déclare la variable 'date', ici comme un objet LocalDate
                        LocalDate date = LocalDate.parse(dateStr, formatter);

                        // Vérifie si c'est un lundi et affiche un message
                        if (date.getDayOfWeek().getValue() == 1) {
                            System.out.println("Etablissement ferme les lundis.");
                        } else {
                            // On extrait le jour comme un entier (1 = lundi, 7 = dimanche)
                            int jour = date.getDayOfWeek().getValue();
                            // on appelle un int
                            etablissement.afficherPlanningPourJour(jour - 1); // jour - 1 car dans le planning, le lundi = index 0 vu que c'est férié
                        }
                    } catch (Exception e) {
                        System.out.println("Format de date invalide, veuillez reessayer.");
                    }
                }
                case "4" -> {
                    // Sauvegarde des données clients et rdv dans des fichiers
                    try {
                        etablissement.sauvegarderClients("clients.txt");
                        etablissement.sauvegarderRendezVous("rendezvous.txt");
                        System.out.println("Donnees sauvegardees !");
                    } catch (IOException e) {
                        System.err.println("Erreur de sauvegarde : " + e.getMessage());
                    }
                }
                case "5" -> {
                    // Chargement des données depuis les fichiers
                    try {
                        etablissement.chargerClients("clients.txt");
                        etablissement.chargerRendezVous("rendezvous.txt");
                        System.out.println("Donnees chargees !");
                    } catch (IOException e) {
                        System.err.println("Erreur de chargement : " + e.getMessage());
                    }
                }
                case "6" -> {
                    // Affichage de texte
                    System.out.println("=============== A propos du programme ===============");
                    System.out.println("Projet EPF - 2A - Semestre 3 - Programmation Java ");
                    System.out.println("Thomas MOROT-GAUDRY & Bryan THIRIMANNA ");
                }

                case "7" -> {
                    // Message de fin et sortie du programme
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide ! Veuillez reessayer.");
            }
        }
    }
}

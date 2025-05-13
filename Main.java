import models.*;
import services.Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Objet> catalogue = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bienvenue dans le système de réservation multitype.");

        boolean actif = true;
        while (actif) {
            afficherMenu();
            String choix = scanner.nextLine().trim().toLowerCase();

            switch (choix) {
                case "a" -> ajouterObjet();
                case "b" -> faireReservation();
                case "c" -> afficherCatalogue();
                case "d" -> {
                    System.out.println("Au revoir !");
                    actif = false;
                }
                default -> System.out.println("Option inconnue.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("a. Enregistrer un bien");
        System.out.println("b. Réserver un bien");
        System.out.println("c. Afficher les biens");
        System.out.println("d. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterObjet() {
        System.out.print("Type (vehicule/habitation) : ");
        String type = scanner.nextLine().trim().toLowerCase();
        System.out.print("Nom du bien : ");
        String nom = scanner.nextLine().trim();

        switch (type) {
            case "vehicule" -> {
                catalogue.add(new Vehicule(nom));
                System.out.println("Véhicule ajouté : " + nom);
            }
            case "habitation" -> {
                catalogue.add(new Habitation(nom));
                System.out.println("Habitation ajoutée : " + nom);
            }
            default -> System.out.println("Type inconnu.");
        }
    }

    private static void faireReservation() {
        if (catalogue.isEmpty()) {
            System.out.println("Aucun bien enregistré.");
            return;
        }

        afficherCatalogue();

        try {
            System.out.print("Numéro du bien à réserver : ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.print("Durée (jours) : ");
            int duree = Integer.parseInt(scanner.nextLine());

            Reservation reservation = new Reservation(catalogue.get(index), duree);
            reservation.afficher();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Entrée invalide.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void afficherCatalogue() {
        if (catalogue.isEmpty()) {
            System.out.println("Aucun bien disponible.");
            return;
        }

        System.out.println("--- Liste des biens ---");
        for (int i = 0; i < catalogue.size(); i++) {
            Objet obj = catalogue.get(i);
            String statut = obj.estDisponible() ? "Libre" : "Réservé";
            System.out.printf("[%d] %s - %s (%s)%n", i, obj.getType(), obj.getNom(), statut);
        }
    }
}

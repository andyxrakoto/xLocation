package services;

import models.Objet;

public class Reservation {
    private Objet objet;
    private int duree;

    public Reservation(Objet objet, int duree) throws Exception {
        if (!objet.estDisponible()) {
            throw new Exception("L'objet '" + objet.getNom() + "' est déjà réservé.");
        }
        this.objet = objet;
        this.duree = duree;
        objet.reserver();
    }

    public void annuler() {
        objet.liberer();
        System.out.println("Réservation annulée pour " + objet.getNom());
    }

    public void afficher() {
        System.out.println("Réservation de " + objet.getType() + " '" + objet.getNom() + "' pour " + duree + " jour(s).");
    }
}

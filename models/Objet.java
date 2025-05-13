package models;

public abstract class Objet {
    protected String nom;
    protected boolean estReserve;

    public Objet(String nom) {
        this.nom = nom;
        this.estReserve = false;
    }

    public boolean reserver() {
        if (estReserve) return false;
        estReserve = true;
        return true;
    }

    public void liberer() {
        estReserve = false;
    }

    public boolean estDisponible() {
        return !estReserve;
    }

    public String getNom() {
        return nom;
    }

    public abstract String getType();
}

package models;

public class Vehicule extends Objet {
    public Vehicule(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return "Véhicule";
    }
}

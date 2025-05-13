package models;

public class Habitation extends Objet {
    public Habitation(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return "Habitation";
    }
}

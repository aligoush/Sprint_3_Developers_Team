package model.entities;


import enums.Thematic;
import enums.Type;

public class Clue extends Item {

    private Thematic thematic;
    private String details;

    public Clue(int id, String name, double price, Type type, Thematic thematic, String details) {
        super(id, name, price, type);
        this.thematic = thematic;
        this.details = details;
    }

    public Clue(int id, String name, double price, int idRoom, Type type, Thematic thematic, String details) {
        super(id, name, price, idRoom, type);
        this.thematic = thematic;
        this.details = details;
    }

    public Thematic getThematic() {
        return thematic;
    }

    public void setThematic(Thematic thematic) {
        this.thematic = thematic;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "\nClue: " + super.toString() + ", Clue{" +
                "thematic=" + thematic +
                ", details='" + details + '\'' +
                '}' + "\n";
    }
}

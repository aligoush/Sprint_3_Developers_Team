package entities;

    

public class Clue extends Item {

    private String thematic;

    private String name;

    public Clue(float price, String thematic, String name) {
        super(price);

        this.thematic = thematic;
        this.name = name;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }
}

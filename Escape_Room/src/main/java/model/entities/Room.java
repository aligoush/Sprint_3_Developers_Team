
package model.entities;

import java.util.ArrayList;

public class Room {

    private String name;
    private int difficulty;
    private double price;
    private String thematic;
    private ArrayList<Item> items;

    public Room(String name,String thematic, int difficulty, double price ) {


        this.name = name;
        this.thematic = thematic;
        this.difficulty = difficulty;
        this.price = price;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

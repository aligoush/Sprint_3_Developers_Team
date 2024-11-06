
package entities;

import java.util.ArrayList;

public class Room {

    private String name;

    private int difficulty;
    private ArrayList<Item> elements;

    public Room(String name, int difficulty, ArrayList<Item> elements) {


        this.name = name;
        this.difficulty = difficulty;
        this.elements = elements;
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

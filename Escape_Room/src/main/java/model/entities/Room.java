
package model.entities;

import java.util.ArrayList;

public class Room {
    private int id;
    private String name;
    private int difficulty;
    private double base_price;
    private String thematic;
    private final int id_escape_room;
    private ArrayList<Item> items;

    public Room(int id, String name, String thematic, int difficulty, double base_price, int id_escape_room ) {

        this.id = id;
        this.name = name;
        this.thematic = thematic;
        this.difficulty = difficulty;
        this.base_price = base_price;
        this.id_escape_room = id_escape_room;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_escape_room() {
        return id_escape_room;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", base_price=" + base_price +
                ", thematic='" + thematic + '\'' +
                '}';
    }
}

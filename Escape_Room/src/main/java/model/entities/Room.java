
package model.entities;

import java.util.ArrayList;

public class Room {
    private int id;
    private String name;
    private int difficulty;
    private double basePrice;
    private String thematic;
    private final int idEscapeRoom;
    private ArrayList<Item> items;

    public Room(int id, String name, String thematic, int difficulty, double basePrice, int idEscapeRoom) {

        this.id = id;
        this.name = name;
        this.thematic = thematic;
        this.difficulty = difficulty;
        this.basePrice = basePrice;
        this.idEscapeRoom = idEscapeRoom;
    }

    public double getBase_price() {
        return basePrice;
    }

    public void setBase_price(double base_price) {
        this.basePrice = base_price;
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

    public int getIdEscapeRoom() {
        return idEscapeRoom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", base_price=" + basePrice +
                ", thematic='" + thematic + '\'' +
                '}';
    }
}

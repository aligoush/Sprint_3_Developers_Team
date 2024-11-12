
package model.entities;

import enums.Thematic;

import java.util.ArrayList;

public class Room {
    private int id;
    private String name;
    private int difficulty;
    private double basePrice;
    private Thematic thematic;
    private final int idEscapeRoom;
    private ArrayList<Item> items;

    public Room(int id, String name, Thematic thematic, int difficulty, double basePrice, int idEscapeRoom) {

        this.id = id;
        this.name = name;
        this.thematic = thematic;
        this.difficulty = difficulty;
        this.basePrice = basePrice;
        this.idEscapeRoom = idEscapeRoom;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Thematic getThematic() {
        return thematic;
    }

    public void setThematic(Thematic thematic) {
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

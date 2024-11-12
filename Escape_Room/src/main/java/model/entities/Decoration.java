package model.entities;


import enums.MaterialType;
import enums.Type;

public class Decoration extends Item {

    private MaterialType material;

    public Decoration(int id, String name, double price, int idRoom, Type type, MaterialType material) {
        super(id, name, price, idRoom, type);
        this.material = material;
    }

    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "material=" + material +
                '}';
    }
}



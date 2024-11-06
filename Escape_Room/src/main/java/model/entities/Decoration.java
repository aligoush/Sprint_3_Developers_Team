package model.entities;


public class Decoration extends Item {


    private String name;
    private String material;
    private String thematic;

    public Decoration(float price, String name, String material, String thematic) {
        super(price);
        this.name = name;
        this.material = material;
        this.thematic = thematic;

    }


    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getMaterial() {
        return material;

    }

    public void setMaterial() {
        this.material = material;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic() {
        this.thematic = thematic;
    }

    @Override
    public String toString() {
        return "Decor{" +
                "Precio: " +
                ", Nombre: '" + name + '\'' +
                ", Materal: " + material + '\'' +
                ", Temática: " + thematic + '\''
                ;
    }
}



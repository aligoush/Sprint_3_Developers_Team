package model.entities;

public class EscapeRoom {

    private int idEscape;
    private String name;

    public EscapeRoom(int idEscape, String name) {
        this.idEscape = idEscape;
        this.name = name;
    }

    public int getIdEscape() {
        return idEscape;
    }

    public void setIdEscape(int idEscape) {
        this.idEscape = idEscape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EscapeRoom{" +
                "idEscape=" + idEscape +
                ", name='" + name + '\'' +
                '}';
    }
}

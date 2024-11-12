package model.entities;

public class EscapeRoom {

    private int idEscapeRoom;
    private String name;

    public EscapeRoom(int idEscapeRoom, String name) {
        this.idEscapeRoom = idEscapeRoom;
        this.name = name;
    }

    public int getIdEscapeRoom() {
        return idEscapeRoom;
    }

    public void setIdEscapeRoom(int idEscapeRoom) {
        this.idEscapeRoom = idEscapeRoom;
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
                "idEscape=" + idEscapeRoom +
                ", name='" + name + '\'' +
                '}';
    }
}

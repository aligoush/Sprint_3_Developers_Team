package model.entities;

public class EscapeRoom {
    private static EscapeRoom instance;
    private int idEscapeRoom;
    private String name;

    private EscapeRoom(int idEscapeRoom, String name) {
        this.idEscapeRoom = idEscapeRoom;
        this.name = name;
    }

    public static EscapeRoom getInstance(){
        if(instance == null){
            instance = new EscapeRoom(1,"Adventures");
        }
        return instance;
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

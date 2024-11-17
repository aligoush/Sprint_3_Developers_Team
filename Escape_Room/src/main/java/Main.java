import view.Menu;

public class Main {
    public static void main(String[] args) {
        //EscapeRoom escapeRoom = new EscapeRoom(1, "Adventures");

        //holi2.startMenu();
        /*EscapeRoomDAOImpl er = new EscapeRoomDAOImpl();
        er.add(escapeRoom);
        System.out.println("Escape Room creado");

        RoomDAOImpl roomDao = new RoomDAOImpl();
        Room newRoom = new Room(0, "Scary Room", "Horror", 3, 4,escapeRoom.getIdEscape());

        roomDao.add(newRoom);
        System.out.println("Room insertado" + newRoom);

        System.out.println("List of rooms");
        for(Room room: roomDao.showAll()){
            System.out.println(room);
        }

        //Room newRoom = new Room(1, "Scary Room", "Horror", 3, 4,escapeRoom.getIdEscape());
        RoomDAOImpl roomDao = new RoomDAOImpl();
        roomDao.delete(1); */
        try{
            Menu.startMenu();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

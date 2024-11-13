package management;

import dao.RoomDAOImpl;
import enums.Thematic;
import model.entities.EscapeRoom;
import model.entities.Room;
import utils.InputUtils;

import java.util.List;

public class RoomManager {

    private static RoomManager instance;
    private RoomDAOImpl roomDao;
    private EscapeRoom escapeRoom;

    private RoomManager(EscapeRoom escapeRoom){
        this.escapeRoom = escapeRoom;
        this.roomDao = new RoomDAOImpl();
    }
    public static RoomManager getInstance(EscapeRoom escapeRoom){
        if(instance == null){
            instance = new RoomManager(escapeRoom);
        }
        return instance;
    }
    public void createRoom(){
        String name = InputUtils.readString("Name of the room: ");
        int difficulty = InputUtils.readInt("Difficulty: ");
        int id = 1;
        Thematic thematic = InputUtils.readEnum("Choose thematic: ", Thematic.class);
 /*       System.out.println("Thematic: \n  "
        + "1. Halloween"
                + "\n2. Science Fiction"
                + "\n3. Christmas"
                + "\n4. Jurassic Park");
        int thematicNumber = InputUtils.readInt("Select the value: \n");
        if(thematicNumber < 1 || thematicNumber > 4){
            throw new Exception("Choose valid number");
        }
        switch (thematicNumber){
            case 1: thematic = Thematic.HALLOWEEN;
            break;
            case 2: thematic = Thematic.SCIENCE_FICTION;
            break;
            case 3: thematic = Thematic.CHRISTMAS;
            break;
            case 4: thematic = Thematic.JURASSIC_PARK;
        }
*/
        double price = InputUtils.readDouble("Price of the room: ");
        Room newRoom = new Room(id,name,thematic, difficulty, price, escapeRoom.getIdEscapeRoom());
        roomDao.createRoom(newRoom);
    }

    public List<Room> showRooms(){
        System.out.println("List of rooms in the DB:");
        List<Room> roomList = roomDao.showAll();
        System.out.println(roomList);
        return roomList;
    }

    public void showInventoryRooms(){
        System.out.println("List of rooms in the DB:");
        List<Room> roomList = roomDao.showAll();
        System.out.println(roomList);
    }
}

package management;

import dao.RoomDAOImpl;
import enums.Thematic;
import model.entities.EscapeRoom;
import model.entities.Room;
import utils.InputUtils;

import java.util.ArrayList;
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
        double price = InputUtils.readDouble("Price of the room: ");
        Room newRoom = new Room(id,name,thematic, difficulty, price, escapeRoom.getIdEscapeRoom());
        roomDao.createRoom(newRoom);
    }

    public List<Integer> getAllRoomsID(){
        List<Room> roomList = roomDao.getAllRooms();
        List<Integer> roomsIds = new ArrayList<>();
        for(Room room: roomList){
            roomsIds.add(room.getId());
        }
        return roomsIds;
    }

    public int getRoomID(){
        List<Integer> ids = getAllRoomsID();
        int idInput = InputUtils.readID("Enter the ID of the room: ", ids);
        return idInput;
    }

    public int getRoomIDByThematic(Thematic thematic){
        List<Integer> ids = getRoomsIDByThematic(thematic);
        int idInput = InputUtils.readID("Enter the ID of the room: ", ids);
        return idInput;
    }

    public List<Integer> getRoomsIDByThematic(Thematic thematic){
        List<Room> rooms = roomDao.getRoomsByThematic(thematic);
        List<Integer> roomsIds = new ArrayList<>();
        for(Room room: rooms){
            roomsIds.add(room.getId());
        }
        return roomsIds;
    }

    public void showAllRooms(){
        System.out.println("List of rooms in the DB:");
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void showRoomsByThematic(Thematic thematic) {
        System.out.println("List of rooms in the DB with thematic: " + thematic);
        List<Room> rooms = null;
        rooms = roomDao.getRoomsByThematic(Thematic.valueOf(thematic.name()));
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void deleteRoom(int id){
        roomDao.delete(id);
    }
}

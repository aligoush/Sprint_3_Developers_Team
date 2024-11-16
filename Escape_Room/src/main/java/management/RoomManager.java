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

    public void showAllRooms(){
        System.out.println("List of rooms in the DB:");
        List<Room> roomList = roomDao.getAllRooms();
        System.out.println(roomList);
    }

    public List<Room> showRoomsByTheme(Thematic thematic){
        System.out.println("List of rooms in the DB with thematic: " + thematic);
        List<Room> roomList = roomDao.showRoomsByThematic(Thematic.valueOf(thematic.name()));
        if (roomList.isEmpty()) {
            System.out.println("No rooms found with matching thematic.");
        }
        System.out.println(roomList);
        return roomList;
    }

    public void deleteRoom(){
        System.out.println("List of rooms in the DB:");
        List<Room> roomList = roomDao.showAll();
        if (roomList.isEmpty()) {
            System.out.println("No rooms found in the DB.");
        }

        System.out.println(roomList);

        Room selectedRoom = null;
        int idRoom = 0;
        while (selectedRoom == null){
            idRoom = InputUtils.readInt("Choose the Room ID you want to delete: ");
            int finalIdRoom = idRoom;
            selectedRoom = roomList.stream()
                    .filter(room -> room.getId() == finalIdRoom)
                    .findFirst()
                    .orElse(null);
            if (selectedRoom == null){
                System.out.println("Invalid ID Room. Try again.");
            }
        }

        roomDao.delete(idRoom);
    }
}

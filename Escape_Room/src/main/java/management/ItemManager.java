package management;

import dao.impl.ItemDAOImpl;
import dao.impl.RoomDAOImpl;
import enums.MaterialType;
import enums.Thematic;
import enums.Type;
import exceptions.NoAvailableCluesException;
import exceptions.NoAvailableDecosException;
import model.entities.*;
import utils.InputUtils;

import java.util.List;

public class ItemManager {
    private static ItemManager instance;
    private ItemDAOImpl itemDao;
    private RoomDAOImpl roomDao;
    private EscapeRoom escapeRoom;
    private RoomManager roomManager;

    private ItemManager(RoomManager roomManager){
        this.itemDao = new ItemDAOImpl();
        this.roomDao = new RoomDAOImpl();
        this.roomManager = roomManager;
    }

    public static ItemManager getInstance(RoomManager roomManager){
        if(instance == null){
            instance = new ItemManager(roomManager);
        }
        return instance;
    }

    public void createClue(){
        String name = InputUtils.readString("Name of the clue: ");
        double price = InputUtils.readDouble("Price of the clue: ");
        Type type = Type.CLUE;
        int id = 1;
        Thematic thematic = InputUtils.readEnum("Choose thematic: ", Thematic.class);
        String details = InputUtils.readString("Define details of the clue: ");
        Clue clue = new Clue(id, name, price,  type,  thematic, details);
        itemDao.create(clue);
    }

    public void createDecoration(){
        String name = InputUtils.readString("Name of the decoration: ");
        double price = InputUtils.readDouble("Price of the decoration: ");
        Type type = Type.DECORATION;
        int id = 1;
        MaterialType material = InputUtils.readEnum("Choose material: ", MaterialType.class);
        Decoration decoration = new Decoration(id, name, price, type, material);
        itemDao.create(decoration);
    }

    public void showAvailableClues() throws NoAvailableCluesException {
        System.out.println("Clues in the DB with idRoom(NULL):");
        List<Clue> clues = itemDao.showAvailableClues();
        if (clues.isEmpty()){
            throw new NoAvailableCluesException("Error. There are no available clues in the DB. You must create a new clue.");
        }

        System.out.println(clues);

        Clue selectedClue = null;
        int idClue = 0;
        while (selectedClue == null) {
            idClue = InputUtils.readInt("Choose the Clue ID you want to add: ");
            int finalIdClue = idClue;
            selectedClue = clues.stream()
                    .filter(clue -> clue.getId() == finalIdClue)
                    .findFirst()
                    .orElse(null);
            if (selectedClue == null){
                System.out.println("Invalid ID Clue. Try again.");
            }
        }

        List<Room> rooms = roomManager.showRoomsByTheme(selectedClue.getThematic());
        Room selectedRoom = null;
        int idRoom = 0;
        while (selectedRoom == null){
            idRoom = InputUtils.readInt("Choose the Room ID you want to add the clue to: ");
            int finalIdRoom = idRoom;
            selectedRoom = rooms.stream()
                    .filter(room -> room.getId() == finalIdRoom)
                    .findFirst()
                    .orElse(null);
            if (selectedRoom == null){
                System.out.println("Invalid ID Room. Try again.");
            }
        }

        itemDao.updateItemRoom(idClue, idRoom);
    }

    public void showInventoryClues(){
        System.out.println("Clues in the DB:");
        List<Clue> clues = itemDao.showAllClues();
        System.out.println(clues);
    }

    public void showAvailableDecos() throws NoAvailableDecosException {
        System.out.println("Decorations in the DB with idRoom(NULL):");
        List<Decoration> decos = itemDao.showAvailableDecos();
        if (decos.isEmpty()){
            throw new NoAvailableDecosException("Error. There are no available decorations in the DB. You must create a new decoration.");
        }

        System.out.println(decos);

        Decoration selectedDeco = null;
        int idDeco = 0;
        while (selectedDeco == null){
            idDeco = InputUtils.readInt("Choose the Decoration ID you want to add: ");
            int finalIdDeco = idDeco;
            selectedDeco = decos.stream()
                    .filter(decoration -> decoration.getId() == finalIdDeco)
                    .findFirst()
                    .orElse(null);
            if (selectedDeco == null){
                System.out.println("Invalid ID Decoration. Try again.");
            }
        }

//        roomManager.showAllRooms();
//        Room selectedRoom = null;
//        int idRoom = 0;
//        while (selectedRoom == null){
//            idRoom = InputUtils.readInt("Choose the Room ID you want to add the decoration to: ");
//            int finalIdRoom = idRoom;
//            selectedRoom = rooms.stream()
//                    .filter(room -> room.getId() == finalIdRoom)
//                    .findFirst()
//                    .orElse(null);
//            if (selectedRoom == null){
//                System.out.println("Invalid ID Room. Try again.");
//            }
//        }
//
//        //List<Room> rooms = roomManager.showRooms();
//        //int idRoom = InputUtils.readInt("Choose the Room ID you want to add the decoration to: ");
//        itemDao.updateItemRoom(idDeco, idRoom);

    }

    public void showInventoryDecos(){
        System.out.println("Decorations in the DB:");
        List<Decoration> decos = itemDao.showAllDecos();
        System.out.println(decos);
    }


    public void deleteClue() throws NoAvailableCluesException {
        System.out.println("Clues in the DB:");
        List<Clue> clues = itemDao.showAllClues();
        if (clues.isEmpty()){
            throw new NoAvailableCluesException("Error. There are no available clues in the DB.");
        }

        System.out.println(clues);

        Clue selectedClue = null;
        int idClue = 0;
        while (selectedClue == null){
            idClue = InputUtils.readInt("Choose the Clue ID you want to delete: ");
            int finalIdClue = idClue;
            selectedClue = clues.stream()
                    .filter(clue -> clue.getId() == finalIdClue)
                    .findFirst()
                    .orElse(null);
            if (selectedClue == null){
                System.out.println("Invalid ID Clue. Try again.");
            }
        }

        itemDao.deleteItem(idClue);
    }

    public void deleteDeco() throws NoAvailableDecosException {
        System.out.println("Decorations in the DB:");
        List<Decoration> decos = itemDao.showAllDecos();
        if (decos.isEmpty()){
            throw new NoAvailableDecosException("Error. There are no decorations in the DB.");
        }

        System.out.println(decos);

        Decoration selectedDeco = null;
        int idDeco = 0;
        while (selectedDeco == null){
            idDeco = InputUtils.readInt("Choose the Decoration ID you want to delete: ");
            int finalIdDeco = idDeco;
            selectedDeco = decos.stream()
                    .filter(decoration -> decoration.getId() == finalIdDeco)
                    .findFirst()
                    .orElse(null);
            if (selectedDeco == null){
                System.out.println("Invalid ID Decoration. Try again.");
            }
        }

        itemDao.deleteItem(idDeco);
    }


}

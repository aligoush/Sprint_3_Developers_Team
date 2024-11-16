package management;

import dao.ItemDAOImpl;
import dao.RoomDAOImpl;
import enums.MaterialType;
import enums.Thematic;
import enums.Type;
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

    public void showAvailableClues(){
        System.out.println("Clues in the DB with idRoom(NULL):");
        List<Item> clues = itemDao.showAvailableClues();
        System.out.println(clues);
        int idClue = InputUtils.readInt("Clue id to add: ");
        roomManager.showAllRooms();
        int idRoom = InputUtils.readInt("Room id to add the clue: ");
        itemDao.updateClueRoom(idClue, idRoom);

    }

    public void showInventoryClues(){
        System.out.println("Clues in the DB:");
        List<Item> clues = itemDao.showAllClues();
        System.out.println(clues);
    }

    public void showAvailableDecos(){
        System.out.println("Decorations in the DB with idRoom(NULL):");
        List<Item> decos = itemDao.showAvailableDecos();
        System.out.println(decos);
        int idDeco = InputUtils.readInt("Decoration id to add: ");
        roomManager.showAllRooms();
        int idRoom = InputUtils.readInt("Room id to add the decoration: ");
        itemDao.updateDecoRoom(idDeco, idRoom);

    }

    public void showInventoryDecos(){
        System.out.println("Decorations in the DB:");
        List<Item> decos = itemDao.showAllDecos();
        System.out.println(decos);
    }


}

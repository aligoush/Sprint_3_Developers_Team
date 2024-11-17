package management;

import dao.ItemDAOImpl;
import dao.RoomDAOImpl;
import enums.MaterialType;
import enums.Thematic;
import enums.Type;
import exceptions.NoAvailableCluesException;
import model.entities.*;
import utils.InputUtils;

import java.util.ArrayList;
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

    public void showAllClues() throws NoAvailableCluesException {
        System.out.println("Clues in the DB:");
        List<Clue> clues = itemDao.getAllClues();
        if (clues.isEmpty()){
            throw new NoAvailableCluesException("There are no clues in the DB. Please, create a new one.");
        }
        for (Clue clue : clues) {
            System.out.println(clue);
        }
    }

    public void showAvailableClues() {
        System.out.println("Clues in the DB with idRoom(NULL):");
        List<Clue> clues = itemDao.getAvailableClues();
        for (Clue clue : clues) {
            System.out.println(clue);
        }
    }

    public void showAllDecos(){
        System.out.println("Decorations in the DB:");
        List<Decoration> decos = itemDao.getAllDecos();
        for (Decoration decoration: decos) {
            System.out.println(decoration);
        }
    }


    public void showAvailableDecos() {
        System.out.println("Decorations in the DB with idRoom(NULL):");
        List<Decoration> decos = itemDao.getAvailableDecos();
        for (Decoration decoration : decos) {
            System.out.println(decoration);
        }
    }

    public int getClueID() {
        List<Integer> ids = getAllCluesID();
        int idInput = InputUtils.readID("Enter the ID of the clue: ", ids);
        return idInput;
    }

    public List<Integer> getAllCluesID() {
        List<Clue> clues = itemDao.getAllClues();
        List<Integer> cluesIds = new ArrayList<>();
        for (Clue clue : clues){
            cluesIds.add(clue.getId());
        }
        return cluesIds;
    }

    public int getAvailableClueID() {
        List<Integer> ids = getAvailableCluesID();
        int idInput = InputUtils.readID("Enter the ID of the clue: ", ids);
        return idInput;
    }

    public List<Integer> getAvailableCluesID() {
        List<Clue> clues = itemDao.getAvailableClues();
        List<Integer> cluesIds = new ArrayList<>();
        for(Clue clue : clues){
            cluesIds.add(clue.getId());
        }
        return cluesIds;
    }

    public Thematic getThematicClueByID(int idClue) {
        Clue clue = itemDao.getClueByID(idClue);
        return clue.getThematic();
    }

    public int getDecoID() {
        List<Integer> ids = getAllDecosID();
        int idInput = InputUtils.readID("Enter the ID of the decoration: ", ids);
        return idInput;
    }

    public List<Integer> getAllDecosID() {
        List<Decoration> decos = itemDao.getAllDecos();
        List<Integer> decosIds = new ArrayList<>();
        for (Decoration decoration : decos){
            decosIds.add(decoration.getId());
        }
        return decosIds;
    }

    public int getAvailableDecoID() {
        List<Integer> ids = getAvailableDecosID();
        int idInput = InputUtils.readID("Enter the ID of the decoration: ", ids);
        return idInput;
    }

    public List<Integer> getAvailableDecosID() {
        List<Decoration> decos = itemDao.getAvailableDecos();
        List<Integer> decosIds = new ArrayList<>();
        for(Decoration decoration : decos){
            decosIds.add(decoration.getId());
        }
        return decosIds;
    }


    public void assignClueToRoom(int idClue, int idRoom){
        itemDao.assignItemRoom(idClue, idRoom);
    }

    public void assignDecoToRoom(int idDeco, int idRoom){
        itemDao.assignItemRoom(idDeco, idRoom);
    }


    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }

}

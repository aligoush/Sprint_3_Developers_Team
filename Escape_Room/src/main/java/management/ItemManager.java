package management;

import dao.ItemDAOImpl;
import enums.MaterialType;
import enums.Thematic;
import enums.Type;
import model.entities.Clue;
import model.entities.Decoration;
import model.entities.EscapeRoom;
import utils.InputUtils;

public class ItemManager {
    private static ItemManager instance;
    private ItemDAOImpl itemDao;
    private EscapeRoom escapeRoom;
    private ItemManager(){
        this.itemDao = new ItemDAOImpl();
    }
    public static ItemManager getInstance(){
        if(instance == null){
            instance = new ItemManager();
        }
        return instance;
    }
    public void createClue(){
        String name = InputUtils.readString("Name of the clue: ");
        double price = InputUtils.readDouble("Price of the clue: ");
        Type type = Type.CLUE;
        int idRoom = Integer.parseInt(null);
        int id = 1;
        Thematic thematic = InputUtils.readEnum("Choose thematic: ", Thematic.class);
        String details = InputUtils.readString("Define details of the clue: ");
        Clue clue = new Clue(id, name, price, idRoom, type,  thematic, details);
        itemDao.createItem(clue);
    }

    public void createDecoration(){
        String name = InputUtils.readString("Name of the decoration: ");
        double price = InputUtils.readDouble("Price of the decoration: ");
        Type type = Type.DECORATION;
        int idRoom = Integer.parseInt(null);
        int id = 1;
        MaterialType material = InputUtils.readEnum("Choose material: ", MaterialType.class);
        Decoration decoration = new Decoration(id, name, price, idRoom, type,  material);
        itemDao.createItem(decoration);
    }


}

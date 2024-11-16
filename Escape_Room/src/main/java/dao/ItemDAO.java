package dao;

import model.entities.Clue;
import model.entities.Decoration;
import model.entities.Item;

import java.util.List;

public interface ItemDAO {
    List<Clue> showAvailableClues();
    List<Decoration> showAvailableDecos();
    List<Clue> showAllClues();
    List<Decoration> showAllDecos();
    void create(Item item);
    //void updateClueRoom(int idClue, int idRoom);
    //void updateDecoRoom(int idDeco, int idRoom);
    void updateItemRoom(int id, int idRoom);
    void deleteItem(int id);
}

package dao;

import model.entities.Clue;
import model.entities.Decoration;
import model.entities.Item;

import java.util.List;

public interface ItemDAO {
    List<Clue> getAvailableClues();
    Clue getClueByID(int id);
    List<Clue> getAllClues();
    List<Decoration> getAvailableDecos();
    List<Decoration> getAllDecos();
    void create(Item item);
    int createItem(Item item);
    void createClue(Clue clue, int idItem);
    void createDecoration(Decoration deco, int idItem);
    void assignItemRoom(int id, int idRoom);
    void deleteItem(int id);
}

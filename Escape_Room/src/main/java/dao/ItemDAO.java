package dao;

import model.entities.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> showAvailableClues();
    List<Item> showAvailableDecos();
    List<Item> showAllClues();
    List<Item> showAllDecos();
    void create(Item item);
    void updateClueRoom(int idClue, int idRoom);
    void updateDecoRoom(int idDeco, int idRoom);
    void delete(int id);
}

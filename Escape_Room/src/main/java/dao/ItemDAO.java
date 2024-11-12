package dao;

import model.entities.Item;

import java.util.List;

public interface ItemDAO {
    //Room findById(int id);
    //List<Item> showAll();
    void add(Item item);

    void update(Item item);

    void delete(int id);
}

package dao;

import model.entities.Room;

import java.util.List;

public interface RoomDAO {
    //Room findById(int id);
    List<Room> showAll();
    void add(Room room);
    //void update(Room room);
    void remove(int id);
}

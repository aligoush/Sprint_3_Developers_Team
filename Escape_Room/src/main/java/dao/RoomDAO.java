package dao;

import model.entities.Room;

import java.util.List;

public interface RoomDAO {
    //Room findById(int id);
    List<Room> showAll();

    void createRoom(Room room);

    //void update(Room room);
    void delete(int id);
}

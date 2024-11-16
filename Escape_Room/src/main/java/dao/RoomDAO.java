package dao;

import enums.Thematic;
import model.entities.Room;

import java.util.List;

public interface RoomDAO {
    //Room findById(int id);
    List<Room> showAll();
    List<Room> showRoomsByThematic(Thematic thematic);
    void createRoom(Room room);

    //void update(Room room);
    void delete(int id);
}

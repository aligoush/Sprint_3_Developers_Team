package dao;

import enums.Thematic;
import exceptions.NoAvailableRoomsException;
import model.entities.Room;

import java.util.List;

public interface RoomDAO {

    List<Room> getRoomsByThematic(Thematic thematic) throws NoAvailableRoomsException;
    List<Room> getAllRooms();
    void createRoom(Room room);
    void delete(int id);

}

package dao;

import model.entities.Room;

import java.sql.Connection;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private Connection connection;

    public RoomDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> showAll() {
        return List.of();
    }

    @Override
    public void add(Room room) {

    }

    @Override
    public void update(Room room) {

    }

    @Override
    public void remove(int id) {

    }
}

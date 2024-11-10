package dao;

import model.entities.Room;
import utils.InputUtils;
import dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl extends DBConnection implements RoomDAO {
    private DBConnection connection;

    public RoomDAOImpl(Connection connection){
        this.connection = connection;
    }

    /*
    @Override
    public Room findById(int id) {
        DBConnection dbConnection = new DBConnection();
        ResultSet rs = null;
        Room room = null;
        int roomId;
        String sql = "SELECT * FROM room WHERE id_room = ?";

        roomId = InputUtils.readInt("Room id: ");

        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, roomId);
            rs = preparedStatement.executeQuery();

            if (rs.next()){
                room = new Room();
                room.set
            }
        }

        return ;
    }
    */

    @Override
    public List<Room> showAll() {
        List<Room> rooms = new ArrayList<>();
        DBConnection connection = new DBConnection();
        String sql = "SELECT * FROM room";
        try (PreparedStatement prepStmt = connection.getConnection().prepareStatement(sql)){
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                Room room = new Room();
                room.setName(rs.getString("name"));
                //lo mismo para el resto de campos
                rooms.add(room);
            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        }

        return rooms;
    }

    @Override
    public void add(Room room) {
        DBConnection connection = new DBConnection();
        String sql = "INSERT INTO room (id, name,.....) VALUES (?,?,....)";
        try (PreparedStatement prepStmt = connection.getConnection().prepareStatement(sql)){
            prepStmt.setInt(1, room.getId);
            //lo mismo para el resto de campos
            prepStmt.executeUpdate();
            System.out.println("Room created");
        } catch (SQLException e) {
            System.out.println("Error inserting the room into DB. " + e.getMessage());
        }
    }

    @Override
    public void update(Room room) {
        DBConnection connection = new DBConnection();
        //String sql = "UPDATE room SET id = ?, name = ?.... WHERE id = ?";
        try (PreparedStatement prepStmt = connection.getConnection().prepareStatement(sql)){
            prepStmt.setString(1, room.getId);
            //lo mismo para el resto de campos
            prepStmt.executeUpdate();
            System.out.println("Room updated");
        } catch (SQLException e) {
            System.out.println("Error updating the room into DB. " + e.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        DBConnection connection = new DBConnection();
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedSatement prepStmt = connection.getConnection().prepareStatement(sql)){
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
            System.out.println("Room removed.");
        } catch (SQLExecption e) {
            System.out.println("Error removing romm from DB. " + e.getMessage);
        }
    }
}

package dao;

import model.entities.Room;
import utils.InputUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {


    /*
    @Override
    public Room findById(int id) {
        DBConnection dbConnection = new DBConnection();
        ResultSet rs = null;
        Room room = null;
        int roomId;
        String sql = "SELECT * FROM room WHERE id_room = ?";

        roomId = InputUtils.readInt("Room id: ");

        try (PreparedStatement = connection.getConnection().prepareStatement(sql)){
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
        String query = "SELECT * FROM rooms";
        try (Connection connection = MySQLConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()){
                rooms.add(new Room(rs.getInt("id_room"), rs.getString("name"),
                        rs.getString("theme"), rs.getInt("difficulty_level"), rs.getDouble("base_price"), rs.getInt("id_escape_room")));
            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public void add(Room room) {
        String query = "INSERT INTO rooms (name, theme, difficulty_level, base_price, id_escape_room) VALUES (?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1,room.getName());
            stmt.setString(2,room.getThematic());
            stmt.setInt(3,room.getDifficulty());
            stmt.setDouble(4,room.getBase_price());
            stmt.setInt(5, room.getId_escape_room());

            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    room.setId(generatedId);
                    System.out.println("Room created with ID: " + generatedId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting the room into DB. " + e.getMessage());
        }
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE rooms SET id = ?, name = ?.... WHERE id = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, room.getName());
            //lo mismo para el resto de campos
            stmt .executeUpdate();
            System.out.println("Room updated");
        } catch (SQLException e) {
            System.out.println("Error updating the room into DB. " + e.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Room removed.");
        } catch (SQLException e) {
            System.out.println("Error removing romm from DB. " + e.getMessage());
        }
    }


}

package dao;

import enums.Thematic;
import model.entities.Room;

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
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection connection = MySQLConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                rooms.add(new Room(rs.getInt("id_room"), rs.getString("name"),
                        Thematic.valueOf(rs.getString("thematic").toUpperCase()), rs.getInt("difficulty"), rs.getDouble("base_price"), rs.getInt("id_escape_room")));
            }
        } catch (SQLException e) {
            System.out.println("Error extracting data: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public void createRoom(Room room) {
        String query = "INSERT INTO rooms (name, thematic, difficulty, base_price, id_escape_room) VALUES (?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getThematic().name());
            stmt.setInt(3, room.getDifficulty());
            stmt.setDouble(4, room.getBasePrice());
            stmt.setInt(5, room.getIdEscapeRoom());

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

    /*
        @Override
        public void update(Room room) {
            String query = "UPDATE rooms SET id = ?, name = ?.... WHERE id = ?";
            try (Connection conn = MySQLConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, room.getName());

                stmt .executeUpdate();
                System.out.println("Room updated");
            } catch (SQLException e) {
                System.out.println("Error updating the room into DB. " + e.getMessage());
            }
        }
    */
    @Override
    public void delete(int id) {
        String query = "DELETE FROM rooms WHERE id_room = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Room removed.");
        } catch (SQLException e) {
            System.out.println("Error removing romm from DB. " + e.getMessage());
        }
    }
}

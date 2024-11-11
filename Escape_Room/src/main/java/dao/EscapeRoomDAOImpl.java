package dao;

import model.entities.EscapeRoom;
import model.entities.Room;

import java.sql.*;

public class EscapeRoomDAOImpl implements EscapeRoomDAO {

    @Override
    public void add(EscapeRoom escapeRoom) {
        String query = "INSERT INTO escape_room (name) VALUES (?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1,escapeRoom.getName());

            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    escapeRoom.setIdEscape(generatedId);
                    System.out.println("Escape Room created with ID: " + generatedId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting the Escape Room into DB. " + e.getMessage());
        }
    }
}

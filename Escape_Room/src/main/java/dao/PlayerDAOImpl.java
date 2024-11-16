package dao;

import enums.Thematic;
import exceptions.PlayerAlreadyExistsException;
import model.entities.Player;
import model.entities.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO{

    @Override
    public List<Player> getAllPlayers(){
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM players";
        try (Connection connection = MySQLConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("id_player"), rs.getString("name"),
                        rs.getBoolean("subscription"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println("Error extracting data: " + e.getMessage());
        }
        return players;
    }

    @Override
    public void createPlayer(Player player) {
        String existingPlayer = "SELECT COUNT(*) FROM players WHERE email = ?";
        String insertQuery = "INSERT INTO players (name, subscription, email ) VALUES (?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection()){

            try(PreparedStatement checkStmt = conn.prepareStatement(existingPlayer)){
                checkStmt.setString(1, player.getEmail());
                try(ResultSet rs = checkStmt.executeQuery()){
                    if(rs.next() && rs.getInt(1)>0){
                        throw new PlayerAlreadyExistsException("Error: a player with this email is already registered.");
                    }
                }
            }

            try(PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
                insertStmt.setString(1, player.getName());
                insertStmt.setBoolean(2, player.getSubscription());
                insertStmt.setString(3, player.getEmail());

                insertStmt.executeUpdate();

                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        player.setIdPlayer(generatedId);
                        System.out.println("Player registered with ID: " + generatedId);
                    }
                }
            }
        } catch (SQLException | PlayerAlreadyExistsException e) {
            System.out.println("Error inserting the room into DB. " + e.getMessage());
        }
    }

    @Override
    public void assignPlayerToRoom(int idPlayer, int idRoom) {
        String query = "INSERT INTO player_is_playing (id_player, id_room, play_date) VALUES (?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 1);
            Timestamp playDate = new Timestamp(calendar.getTimeInMillis());

            stmt.setInt(1, idPlayer);
            stmt.setInt(2, idRoom);
            stmt.setTimestamp(3, playDate);

            stmt.executeUpdate();
            System.out.println("Player was successfully added to the room");
        } catch (SQLException e) {
            System.out.println("Error assigning player to the room. " + e.getMessage());
        }
    }

}

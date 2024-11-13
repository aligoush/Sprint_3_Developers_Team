package dao;

import exceptions.PlayerAlreadyExistsException;
import model.entities.Player;

import java.sql.*;

public class PlayerDAOImpl implements PlayerDAO{


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
    public void addPlayerToRoom(int id) {

    }
}

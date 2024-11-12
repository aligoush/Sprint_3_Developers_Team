package dao;

import model.entities.Clue;
import model.entities.Decoration;
import model.entities.Item;

import java.sql.*;

public class ItemDAOImpl implements ItemDAO {

    //@Override
    /*public List<Clue> showAll() {
        List<Room> clues = new ArrayList<>();
        String query = "SELECT * FROM clues";
        try (Connection connection = MySQLConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()){
                clues.add(new Clue(rs.getInt("id_room"), rs.getString("name"),
                        rs.getString("theme"), rs.getInt("difficulty"), rs.getDouble("base_price"), rs.getInt("id_escape_room")));
            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        }
        return clues;
    }*/

    @Override
    public void add(Item item) {
        addItem(item);
        if (item instanceof Clue) {
            addClue((Clue) item);
        } else if (item instanceof Decoration) {
            addDecoration((Decoration) item);
        }
    }

    public void addItem(Item item) {
        String query = "INSERT INTO items (name_item, price, id_room, type) VALUES (?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getIdRoom());
            stmt.set(4, item.getType());
            stmt.setInt(5, item.getId_escape_room());

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

    public void addClue(Clue clue) {
        String query = "INSERT INTO clues (, theme, difficulty, base_price, id_escape_room) VALUES (?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getThematic());
            stmt.setInt(3, room.getDifficulty());
            stmt.setDouble(4, room.getBase_price());
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

    public void addDecoration(Decoration deco) {
        String query = "INSERT INTO clues (, theme, difficulty, base_price, id_escape_room) VALUES (?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getThematic());
            stmt.setInt(3, room.getDifficulty());
            stmt.setDouble(4, room.getBase_price());
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
    public void update(Item clue) {

    }

    @Override
    public void delete(int id) {

    }
}


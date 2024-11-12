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
    public void create(Item item) {
        int idItem = createItem(item);
        if (item instanceof Clue) {
            createClue((Clue) item, idItem);
        } else if (item instanceof Decoration) {
            createDecoration((Decoration) item, idItem);
        }
    }


    public int createItem(Item item) {
        int idItem = 0;
        String query = "INSERT INTO items (name_item, price, id_room, type) VALUES (?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getIdRoom());
            stmt.setString(4, item.getType().name());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    idItem = generatedId;
                    item.setId(generatedId);
                    System.out.println("Item created with ID: " + generatedId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting the item into DB. " + e.getMessage());
        }
        return idItem;
    }

    public void createClue(Clue clue, int idItem) {
        String query = "INSERT INTO clues (id_item, thematic, details) VALUES (?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, idItem);
            stmt.setString(2, clue.getThematic().name());
            stmt.setString(2, clue.getDetails());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the clue into DB. " + e.getMessage());
        }
    }

    public void createDecoration(Decoration deco, int idItem) {
        String query = "INSERT INTO decorations (id_item, material_type) VALUES (?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, idItem);
            stmt.setString(2, deco.getMaterial().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting the decoration into DB. " + e.getMessage());
        }
    }



    @Override
    public void update(Item clue) {

    }

    @Override
    public void delete(int id) {

    }
}


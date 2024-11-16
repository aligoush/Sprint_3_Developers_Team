package dao;

import enums.MaterialType;
import enums.Thematic;
import enums.Type;
import model.entities.Clue;
import model.entities.Decoration;
import model.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {



    @Override
    public List<Clue> showAvailableClues() {
        List<Clue> clues = new ArrayList<>();
        String query = "SELECT items.*, clues.thematic, clues.details FROM items INNER JOIN clues ON clues.id_item = items.id_item WHERE items.type = 'CLUE' AND items.id_room IS NULL";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Type type = Type.valueOf(rs.getString("type"));
                Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
                    clues.add(new Clue(rs.getInt("id_item"),
                            rs.getString("name_item"),
                            rs.getDouble("price"),
                            rs.getInt("id_room"),
                            type,
                            thematic,
                            rs.getString("details")));

            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error converting data to enum: " + e.getMessage());
        }
        return clues;
    }

    @Override
    public List<Clue> showAllClues() {
        List<Clue> clues = new ArrayList<>();
        String query = "SELECT items.*, clues.thematic, clues.details FROM items INNER JOIN clues ON clues.id_item = items.id_item WHERE items.type = 'CLUE'";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Type type = Type.valueOf(rs.getString("type"));
                Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
                clues.add(new Clue(rs.getInt("id_item"),
                        rs.getString("name_item"),
                        rs.getDouble("price"),
                        rs.getInt("id_room"),
                        type,
                        thematic,
                        rs.getString("details")));

            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error converting data to enum: " + e.getMessage());
        }
        return clues;
    }

    @Override
    public List<Decoration> showAvailableDecos() {
        List<Decoration> decos = new ArrayList<>();
        String query = "SELECT items.*, decorations.material_type FROM items INNER JOIN decorations ON decorations.id_item = items.id_item AND items.id_room IS NULL";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Type type = Type.valueOf(rs.getString("type"));
                MaterialType materialType = MaterialType.valueOf(rs.getString("material_type"));
                decos.add(new Decoration(rs.getInt("id_item"),
                        rs.getString("name_item"),
                        rs.getDouble("price"),
                        rs.getInt("id_room"),
                        type,
                        materialType));

            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error converting data to enum: " + e.getMessage());
        }
        return decos;
    }

    @Override
    public List<Decoration> showAllDecos() {
        List<Decoration> decos = new ArrayList<>();
        String query = "SELECT items.*, decorations.material_type FROM items INNER JOIN decorations ON decorations.id_item = items.id_item WHERE items.type = 'DECORATION'";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Type type = Type.valueOf(rs.getString("type"));
                MaterialType materialType = MaterialType.valueOf(rs.getString("material_type"));
                decos.add(new Decoration(rs.getInt("id_item"),
                        rs.getString("name_item"),
                        rs.getDouble("price"),
                        rs.getInt("id_room"),
                        type,
                        materialType));

            }
        } catch (SQLException e){
            System.out.println("Error extracting data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error converting data to enum: " + e.getMessage());
        }
        return decos;
    }

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
            stmt.setNull(3, Types.INTEGER);
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
            stmt.setString(3, clue.getDetails());

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


    /*public void updateClueRoom(int idClue, int idRoom) {
        String query = "UPDATE items SET id_room = ? WHERE id_item = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRoom);
            stmt.setInt(2, idClue);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }

    public void updateDecoRoom(int idDeco, int idRoom) {
        String query = "UPDATE items SET id_room = ? WHERE id_item = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRoom);
            stmt.setInt(2, idDeco);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }*/

    public void updateItemRoom(int id, int idRoom) {
        String query = "UPDATE items SET id_room = ? WHERE id_item = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRoom);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(int id) {
        String query = "DELETE FROM items WHERE id_item = ?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Item removed.");

        } catch (SQLException e) {
            System.out.println("Error removing room from DB. " + e.getMessage());
        }
    }
}


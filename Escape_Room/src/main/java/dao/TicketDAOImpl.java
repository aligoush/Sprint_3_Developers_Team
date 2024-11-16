package dao;

import enums.Thematic;
import exceptions.PlayerAlreadyExistsException;
import model.entities.Room;
import model.entities.Ticket;

import java.sql.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public void createTicket(Ticket ticket) {

        String insertQuery = "INSERT INTO tickets (sale_date, total_price, id_player ) VALUES (?,?,?)";

        try (
                Connection conn = MySQLConnection.getInstance().getConnection();
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            insertStmt.setDate(1, java.sql.Date.valueOf(ticket.getSaleDate().toLocalDate()));
            insertStmt.setFloat(2, Double.valueOf(ticket.getTotalPrice()).floatValue());
            insertStmt.setInt(3, ticket.getIdPlayer());

            insertStmt.executeUpdate();

            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Ticket registered with ID: " + generatedId);
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Doesnt exist player with id> " + ticket.getIdPlayer());
        } catch (SQLException e) {
            System.out.println("Error inserting the room into DB. " + e.getMessage());
        }

    }

    public double getTotalTicketsPrice() {
        String selectTotalPrice = "SELECT sum(total_price) from tickets";

        try (
                Connection conn = MySQLConnection.getInstance().getConnection();
                PreparedStatement selectStmt = conn.prepareStatement(selectTotalPrice)
        ){
            try(ResultSet rs = selectStmt.executeQuery()){
                if(rs.next()){
                    return rs.getDouble(1);
                }
                return 0;
            } catch (SQLException ex) {
                throw new RuntimeException("Unable to get total ticket price", ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get total ticket price", e);
        }
    }
}
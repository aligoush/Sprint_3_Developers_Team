package dao.impl;

import dao.MySQLConnection;
import dao.TicketDAO;
import model.entities.Ticket;

import java.sql.*;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public int createTicket(Ticket ticket, int idRoom) {
        String calculatePrice = "SELECT r.base_price + IFNULL(SUM(i.price), 0)  FROM rooms r LEFT JOIN items i ON r.id_room = i.id_room WHERE r.id_room = ? ";
        String insertQuery = "INSERT INTO tickets (sale_date, total_price, id_player) VALUES (?,?,?)";

        try (
                Connection conn = MySQLConnection.getInstance().getConnection();
                PreparedStatement calculatePriceStmt = conn.prepareStatement(calculatePrice);
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)
        ) {

            calculatePriceStmt.setInt(1, idRoom);
            try (ResultSet resultSet = calculatePriceStmt.executeQuery()) {
                if(resultSet.next()){
                    double totalPrice = resultSet.getDouble(1);
                    ticket.setTotalPrice(totalPrice);

                    insertStmt.setDate(1, java.sql.Date.valueOf(ticket.getSaleDate().toLocalDate()));
                    insertStmt.setDouble(2, totalPrice);
                    insertStmt.setInt(3, ticket.getIdPlayer());

                    insertStmt.executeUpdate();

                    try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int generatedId = generatedKeys.getInt(1);
                            ticket.setIdTicket(generatedId);
                            System.out.println("Ticket registered with ID: " + generatedId);
                        }
                    }
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Doesn't exist player with ID " + ticket.getIdPlayer());
        } catch (SQLException e) {
            System.out.println("Error inserting the room into DB. " + e.getMessage());
        }
        return ticket.getIdTicket();
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
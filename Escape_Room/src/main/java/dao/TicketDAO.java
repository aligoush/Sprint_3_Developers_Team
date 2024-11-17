package dao;

import model.entities.Ticket;
import java.util.List;

public interface TicketDAO {
    void createTicket(Ticket ticket, int idRoom) ;
    double getTotalTicketsPrice();
}

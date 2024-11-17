package dao;

import model.entities.Ticket;
import java.util.List;

public interface TicketDAO {
    int createTicket(Ticket ticket, int idRoom) ;
    double getTotalTicketsPrice();
}

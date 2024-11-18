package management;

import model.entities.Ticket;
import dao.impl.TicketDAOImpl;
import utils.InputUtils;

public class TicketManager {

    private static TicketManager instance;
    private TicketDAOImpl ticketDAO;
    private Ticket ticket;


    private TicketManager(){
        this.ticketDAO= new TicketDAOImpl();
    }
    public static TicketManager getInstance(){
        if(instance==null){
            instance=new TicketManager();
        }
        return instance;
    }

    public int createTicket(int idPlayer, int idRoom){

        Ticket ticket = new Ticket(idPlayer);
        int idTicket = ticketDAO.createTicket(ticket, idRoom);
        return idTicket;
    }

    public double getTotalTicketsPrice() {

        double totalTicketsPrice = ticketDAO.getTotalTicketsPrice();
        System.out.println("Total price is: " + totalTicketsPrice);
        return totalTicketsPrice;
    }
}

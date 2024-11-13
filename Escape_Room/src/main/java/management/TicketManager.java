package management;

import model.entities.Ticket;
import dao.TicketDAOImpl;
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

    public void createTicket(){
        double totalPrice = InputUtils.readDouble("Total price: ");
        int idPlayer = InputUtils.readInt("Id player: ");

        Ticket ticket=new Ticket(totalPrice, idPlayer);
        ticketDAO.createTicket(ticket);

    }

    public double getTotalTicketsPrice() {

        double totalTicketsPrice = ticketDAO.getTotalTicketsPrice();
        System.out.println("Total price is: " + totalTicketsPrice);
        return totalTicketsPrice;
    }
}

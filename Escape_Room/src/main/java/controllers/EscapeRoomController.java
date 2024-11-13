package controllers;

import dao.EscapeRoomDAOImpl;
import management.ItemManager;
import management.PlayerManager;
import management.RoomManager;
import management.TicketManager;
import model.entities.EscapeRoom;

public class EscapeRoomController {
    private EscapeRoom escapeRoom;
    //private InventoryManager inventoryManager;
    private final RoomManager roomManager;
    private EscapeRoomDAOImpl erdao;
    private ItemManager itemManager;
    private PlayerManager playerManager;
    private TicketManager ticketManager;

    public EscapeRoomController(){
        this.escapeRoom = EscapeRoom.getInstance();
        this.roomManager = RoomManager.getInstance(this.escapeRoom);
        this.erdao = new EscapeRoomDAOImpl();
        this.itemManager = ItemManager.getInstance(this.roomManager);
        this.playerManager = PlayerManager.getInstance();
        this.ticketManager= TicketManager.getInstance();
    }

    public void createRoom() throws Exception {
        erdao.add(this.escapeRoom);
        roomManager.createRoom();
    }

    public void createClue() {
        erdao.add(this.escapeRoom);
        itemManager.createClue();
    }

    public void createDecoration() {
        erdao.add(this.escapeRoom);
        itemManager.createDecoration();
    }

    public void addClueToRoom(){
        itemManager.showAvailableClues();
    }

    public void addDecoToRoom(){
        itemManager.showAvailableDecos();
    }

    public void showInventory(){
        roomManager.showInventoryRooms();
        itemManager.showInventoryClues();
        itemManager.showInventoryDecos();
    }

    public void createPlayer(){
        erdao.add(this.escapeRoom);
        playerManager.createPlayer();
    }

    public void createTicket() {
        ticketManager.createTicket();
    }

    public double getTotalTicketsPrice() {
        return ticketManager.getTotalTicketsPrice();
    }
}

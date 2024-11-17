package controllers;

import dao.impl.EscapeRoomDAOImpl;
import exceptions.NoAvailableCluesException;
import exceptions.NoAvailableDecosException;
import management.*;
import model.entities.EscapeRoom;
import utils.InputUtils;

public class EscapeRoomController {
    private EscapeRoom escapeRoom;
    //private InventoryManager inventoryManager;
    private final RoomManager roomManager;
    private EscapeRoomDAOImpl erdao;
    private ItemManager itemManager;
    private PlayerManager playerManager;
    private TicketManager ticketManager;
    private EscapeRoomManager escapeRoomManager;

    public EscapeRoomController(){
        this.escapeRoom = EscapeRoom.getInstance();
        this.escapeRoomManager = EscapeRoomManager.getInstance();
        this.roomManager = RoomManager.getInstance(this.escapeRoom);
        this.erdao = new EscapeRoomDAOImpl();
        this.itemManager = ItemManager.getInstance(this.roomManager);
        this.playerManager = PlayerManager.getInstance();
        this.ticketManager= TicketManager.getInstance();
    }

    public void createEscapeRoom(){
        escapeRoomManager.createEscapeRoom(this.escapeRoom);
    }

    public void createRoom() throws Exception {
        roomManager.createRoom();
    }

    public void createClue() {
        itemManager.createClue();
    }

    public void createDecoration() {
        itemManager.createDecoration();
    }

    public void addClueToRoom() throws NoAvailableCluesException {
        itemManager.showAvailableClues();
    }

    public void addDecoToRoom() throws NoAvailableDecosException {
        itemManager.showAvailableDecos();
    }

    public void showInventory(){
        roomManager.showAllRooms();
        String enter = InputUtils.readString("Enter to continue.");
        itemManager.showInventoryClues();
        enter = InputUtils.readString("Enter to continue.");
        itemManager.showInventoryDecos();
    }

    public void createPlayer(){
        playerManager.createPlayer();
    }

    public void showAllPlayers() {
        playerManager.showAllPlayers();
    }

    public void addPlayerToRoom(int idPlayer, int idRoom) {
        playerManager.assignPlayerToRoom(idPlayer, idRoom);
    }

    public void delete() throws NoAvailableCluesException, NoAvailableDecosException {
        int option = InputUtils.readInt("Choose an option to delete:\n" +
                "1. Room\n" +
                "2. Clue\n" +
                "3. Decoration");
        switch (option){
            case 1:
                roomManager.deleteRoom();
                break;
            case 2:
                itemManager.deleteClue();
                break;
            case 3:
                itemManager.deleteDeco();
                break;
        }
    }

    public void createTicket(int idPlayer, int idRoom) {
        ticketManager.createTicket(idPlayer, idRoom);
    }

    public double getTotalTicketsPrice() {
        return ticketManager.getTotalTicketsPrice();
    }

    public void addPlayerAndCreateTicket() {
        playerManager.showAllPlayers();
        int idPlayer = playerManager.getPlayerID();
        roomManager.showAllRooms();
        int idRoom = roomManager.getRoomID();
        addPlayerToRoom(idPlayer, idRoom);
        createTicket(idPlayer, idRoom);
    }
}

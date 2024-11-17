package controllers;

import dao.EscapeRoomDAOImpl;
import enums.Thematic;
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

    public void addClueToRoom() {
            itemManager.showAvailableClues();
            int idClue = itemManager.getAvailableClueID();
            Thematic thematic = itemManager.getThematicClueByID(idClue);
            roomManager.showRoomsByThematic(thematic);
            int idRoom = roomManager.getRoomIDByThematic(thematic);
            itemManager.assignClueToRoom(idClue, idRoom);
    }

    public void addDecoToRoom() {
        itemManager.showAvailableDecos();
        int idDeco = itemManager.getAvailableDecoID();
        roomManager.showAllRooms();
        int idRoom = roomManager.getRoomID();
        itemManager.assignDecoToRoom(idDeco, idRoom);
    }

    public void showInventory(){
        roomManager.showAllRooms();
        String enter = InputUtils.readString("Enter to continue.");
        itemManager.showAllClues();
        enter = InputUtils.readString("Enter to continue.");
        itemManager.showAllDecos();
    }

    public void createPlayer(){
        playerManager.createPlayer();
    }

    public void showAllPlayers() {
        playerManager.showAllPlayers();
    }

    public void addPlayerToRoom() {
        playerManager.showAllPlayers();
        int idPlayer = playerManager.getPlayerID();
        roomManager.showAllRooms();
        int idRoom = roomManager.getRoomID();
        playerManager.assignPlayerToRoom(idPlayer, idRoom);
    }

    public void delete() {
        int option = InputUtils.readInt("Choose an option to delete:\n" +
                "1. Room\n" +
                "2. Clue\n" +
                "3. Decoration");
        switch (option){
            case 1:
                roomManager.showAllRooms();
                int idRoom = roomManager.getRoomID();
                roomManager.deleteRoom(idRoom);
                break;
            case 2:
                itemManager.showAllClues();
                int idClue = itemManager.getClueID();
                itemManager.deleteItem(idClue);
                break;
            case 3:
                itemManager.showAllDecos();
                int idDeco = itemManager.getDecoID();
                itemManager.deleteItem(idDeco);
                break;
        }
    }

    public void createTicket() {
        ticketManager.createTicket();
    }

    public double getTotalTicketsPrice() {
        return ticketManager.getTotalTicketsPrice();
    }
}

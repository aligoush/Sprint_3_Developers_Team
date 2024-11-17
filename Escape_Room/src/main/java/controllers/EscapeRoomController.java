package controllers;

import dao.impl.EscapeRoomDAOImpl;
import enums.Thematic;
import exceptions.*;
import management.*;
import model.entities.EscapeRoom;
import utils.InputUtils;

public class EscapeRoomController {
    private EscapeRoom escapeRoom;
    private final RoomManager roomManager;
    private ItemManager itemManager;
    private PlayerManager playerManager;
    private TicketManager ticketManager;
    private EscapeRoomManager escapeRoomManager;

    public EscapeRoomController() {
        this.escapeRoom = EscapeRoom.getInstance();
        this.escapeRoomManager = EscapeRoomManager.getInstance();
        this.roomManager = RoomManager.getInstance(this.escapeRoom);
        this.itemManager = ItemManager.getInstance(this.roomManager);
        this.playerManager = PlayerManager.getInstance();
        this.ticketManager = TicketManager.getInstance();
    }

    public void createEscapeRoom() {
        escapeRoomManager.createEscapeRoom(this.escapeRoom);
    }

    public void createRoom() {
        roomManager.createRoom();
    }

    public void createClue() {
        itemManager.createClue();
    }

    public void createDecoration() {
        itemManager.createDecoration();
    }

    public void addClueToRoom() {
        try {
            itemManager.showAvailableClues();
            int idClue = itemManager.getAvailableClueID();
            Thematic thematic = itemManager.getThematicClueByID(idClue);
            roomManager.showRoomsByThematic(thematic);
            int idRoom = roomManager.getRoomIDByThematic(thematic);
            itemManager.assignClueToRoom(idClue, idRoom);
        } catch (NoAvailableCluesException | NoAvailableRoomsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDecoToRoom() {
        try {
            itemManager.showAvailableDecos();
            int idDeco = itemManager.getAvailableDecoID();
            roomManager.showAllRooms();
            int idRoom = roomManager.getRoomID();
            itemManager.assignDecoToRoom(idDeco, idRoom);
        } catch (NoAvailableDecosException | NoAvailableRoomsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showInventory() {

        boolean isEmpty = true;

        try {
            try{
                roomManager.showAllRooms();
                isEmpty = false;
            } catch (NoAvailableRoomsException e){
                System.out.println(e.getMessage());
            }

            InputUtils.readString("Enter to continue.");

            try {
                itemManager.showAllClues();
                isEmpty = false;
            } catch (NoAvailableCluesException e) {
                System.out.println(e.getMessage());
            }

            InputUtils.readString("Enter to continue.");

            try {
                itemManager.showAllDecos();
                isEmpty = false;
            } catch (NoAvailableDecosException e) {
                System.out.println(e.getMessage());
            }

            if(isEmpty){
                throw new EmptyInventoryException("There are no elements in the inventory.");
            }

        } catch (EmptyInventoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPlayer() {
        playerManager.createPlayer();
    }

    public void showAllPlayers() {
        try {
            playerManager.showAllPlayers();
        } catch (NoAvailablePlayersException e){
            System.out.println(e.getMessage());
        }
    }

    public void addPlayerToRoom(int idPlayer, int idRoom, int idTicket) {
        playerManager.assignPlayerToRoom(idPlayer, idRoom, idTicket);
    }

    public void delete() {
        int option = InputUtils.readInt("Choose an option to delete:\n" +
                "1. Room\n" +
                "2. Clue\n" +
                "3. Decoration");
        try {
            switch (option) {
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
        } catch (NoAvailableCluesException | NoAvailableRoomsException | NoAvailableDecosException e) {
            System.out.println(e.getMessage());
        }
    }

    public int createTicket(int idPlayer, int idRoom) {
        int idTicket = ticketManager.createTicket(idPlayer, idRoom);
        return idTicket;
    }

    public double getTotalTicketsPrice() {
        return ticketManager.getTotalTicketsPrice();
    }

    public void addPlayerAndCreateTicket() {
        try{
            playerManager.showAllPlayers();
            int idPlayer = playerManager.getPlayerID();
            roomManager.showAllRooms();
            int idRoom = roomManager.getRoomID();
            int idTicket = createTicket(idPlayer, idRoom);
            addPlayerToRoom(idPlayer, idRoom, idTicket);
        } catch (NoAvailablePlayersException | NoAvailableRoomsException e){
            System.out.println(e.getMessage());
        }
    }
}

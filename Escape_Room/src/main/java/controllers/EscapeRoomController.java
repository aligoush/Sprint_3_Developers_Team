package controllers;

import dao.EscapeRoomDAOImpl;
import management.ItemManager;
import management.PlayerManager;
import management.RoomManager;
import model.entities.EscapeRoom;

public class EscapeRoomController {
    private EscapeRoom escapeRoom;
    //private InventoryManager inventoryManager;
    private final RoomManager roomManager;
    private EscapeRoomDAOImpl erdao;
    private ItemManager itemManager;
    private PlayerManager playerManager;

    public EscapeRoomController(){
        this.escapeRoom = EscapeRoom.getInstance();
        this.roomManager = RoomManager.getInstance(this.escapeRoom);
        this.erdao = new EscapeRoomDAOImpl();
        this.itemManager = ItemManager.getInstance(this.roomManager);
        this.playerManager = PlayerManager.getInstance();
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
        roomManager.showAllRooms();
        itemManager.showInventoryClues();
        itemManager.showInventoryDecos();
    }

    public void createPlayer(){
        erdao.add(this.escapeRoom);
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
}

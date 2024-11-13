package controllers;

import dao.EscapeRoomDAOImpl;
import management.ItemManager;
import management.RoomManager;
import model.entities.EscapeRoom;

public class EscapeRoomController {
    private EscapeRoom escapeRoom;
    private final RoomManager roomManager;
    private EscapeRoomDAOImpl erdao;
    private ItemManager itemManager;

    public EscapeRoomController(){
        this.escapeRoom = EscapeRoom.getInstance();
        this.roomManager = RoomManager.getInstance(this.escapeRoom);
        this.erdao = new EscapeRoomDAOImpl();
        this.itemManager = ItemManager.getInstance(this.roomManager);
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
}

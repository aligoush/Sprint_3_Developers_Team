package management;

import dao.EscapeRoomDAOImpl;
import dao.ItemDAOImpl;
import dao.RoomDAOImpl;
import model.entities.EscapeRoom;

public class EscapeRoomManager {
    private static EscapeRoomManager instance;
    private EscapeRoomDAOImpl escapeRoomDAO;
    private EscapeRoomManager(){
        this.escapeRoomDAO = new EscapeRoomDAOImpl();
    }

    public static EscapeRoomManager getInstance(){
        if(instance == null){
            instance = new EscapeRoomManager();
        }
        return instance;
    }

    public void createEscapeRoom(EscapeRoom escapeRoom){
        escapeRoomDAO.add(escapeRoom);
    }
}

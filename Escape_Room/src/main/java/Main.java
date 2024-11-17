import dao.EscapeRoomDAOImpl;
import dao.RoomDAOImpl;
import model.entities.EscapeRoom;
import model.entities.Room;
import view.Menu;

public class Main {

    public static void main(String[] args) {

        try {
            Menu.startMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

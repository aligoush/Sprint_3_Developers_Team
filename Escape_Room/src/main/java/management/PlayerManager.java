package management;

import dao.ItemDAOImpl;
import dao.PlayerDAO;
import dao.PlayerDAOImpl;
import model.entities.EscapeRoom;
import model.entities.Player;
import utils.InputUtils;

public class PlayerManager {
        private static PlayerManager instance;
        private PlayerDAOImpl playerDao;

        private PlayerManager(){
            this.playerDao = new PlayerDAOImpl();
        }
        public static PlayerManager getInstance(){
            if(instance == null){
                instance = new PlayerManager();
            }
            return instance;
        }
        public void createPlayer(){
            String name = InputUtils.readString("Name of the player: ");
            boolean subscription = InputUtils.readBoolean("Is player subscribed to the notifications (yes or no)?: ");
            String email = InputUtils.readEmail("Email of the user: ");
            Player player = new Player(name, subscription, email);
            playerDao.createPlayer(player);
        }
}

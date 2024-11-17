package management;

import dao.impl.PlayerDAOImpl;
import model.entities.Player;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static PlayerManager instance;
    private PlayerDAOImpl playerDao;

    private PlayerManager() {
        this.playerDao = new PlayerDAOImpl();
    }

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void createPlayer() {
        String name = InputUtils.readString("Name of the player: ");
        boolean subscription = InputUtils.readBoolean("Is player subscribed to the notifications (yes or no)?: ");
        String email = InputUtils.readEmail("Email of the user: ");
        int id = 1;
        Player player = new Player(id, name, subscription, email);
        playerDao.createPlayer(player);
    }

    public void showAllPlayers() {
        List<Player> players = playerDao.getAllPlayers();
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public List<Integer> getAllPlayersID() {
        List<Player> players = playerDao.getAllPlayers();
        List<Integer> playersIds = new ArrayList<>();
        for(Player player : players){
            playersIds.add(player.getIdPlayer());
        }
        return playersIds;
    }

    public int getPlayerID() {
        List<Integer> ids = getAllPlayersID();
        int idInput = InputUtils.readID("Enter the ID of the player: ", ids);
        return idInput;
    }

    public void assignPlayerToRoom(int idPlayer, int idRoom){
        playerDao.assignPlayerToRoom(idPlayer, idRoom);
    }
}

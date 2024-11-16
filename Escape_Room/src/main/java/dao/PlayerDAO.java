package dao;

import model.entities.Player;

import java.util.List;

public interface PlayerDAO {
    List<Player> getAllPlayers();
    void createPlayer(Player player);
    void assignPlayerToRoom(int idPlayer, int idRoom);

}

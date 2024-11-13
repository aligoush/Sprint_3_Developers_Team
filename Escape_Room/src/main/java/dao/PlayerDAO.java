package dao;

import model.entities.Player;

public interface PlayerDAO {
    void createPlayer(Player player);
    void addPlayerToRoom(int id);
}

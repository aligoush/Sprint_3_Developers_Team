package src.main.java.model.entities;

public class Player {

    private int idPlayer;
    private String name;
    private String subscription;
    private String achievements;

    public int getIdPlayer() {
        return idPlayer;
    }
    public String getName() {
        return name;
    }

    public String getSubscription() {
        return subscription;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String toString() {
        return "Player{" +
                "idPlayer=" + idPlayer +
                ", name='" + name + '\'' +
                ", subscription='" + subscription + '\'' +
                ", achievements='" + achievements + '\'' +
                '}';
    }
}

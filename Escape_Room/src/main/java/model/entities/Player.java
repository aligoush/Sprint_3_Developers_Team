package model.entities;

public class Player {

    private int idPlayer;
    private String name;
    private boolean subscription;
    private String email;

    public Player(int idPlayer, String name, boolean subscription, String email) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.subscription = subscription;
        this.email = email;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id = " + idPlayer +
                ", name = '" + name + '\'' +
                ", subscription = " + subscription +
                ", email = '" + email + '\'' +
                '}';
    }
}

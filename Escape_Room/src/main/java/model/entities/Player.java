package model.entities;

import observer.Observer;
import observer.Subject;

public class Player implements Observer {

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

    public boolean isSubscribed() {
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

    public void update(String message){
        System.out.println("Player"+ name + " recieved the notification "+ message);
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

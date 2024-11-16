package model.entities;

import java.time.LocalDateTime;

public class Ticket {

    private int idTicket;
    private LocalDateTime saleDate;
    private double totalPrice;
    private int idPlayer;

    public Ticket(double totalPrice, int idPlayer) {
        this.totalPrice = totalPrice;
        this.saleDate = LocalDateTime.now();
        this.idPlayer=idPlayer;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", saleDate=" + saleDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

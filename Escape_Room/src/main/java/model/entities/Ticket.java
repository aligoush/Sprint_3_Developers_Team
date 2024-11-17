package model.entities;

import java.time.LocalDateTime;

public class Ticket {

    private int idTicket;
    private LocalDateTime saleDate;
    private double totalPrice;
    private int idPlayer;

    public Ticket(int idPlayer) {
        this.saleDate = LocalDateTime.now();
        this.idPlayer=idPlayer;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setIdTicket(int idTicket){
        this.idTicket = idTicket;
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

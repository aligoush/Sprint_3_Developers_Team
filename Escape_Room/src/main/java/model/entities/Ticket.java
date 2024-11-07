package src.main.java.model.entities;

import java.util.Date;

public class Ticket {

    private int idTicket;
    private Date saleDate;
    private float totalPrice;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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

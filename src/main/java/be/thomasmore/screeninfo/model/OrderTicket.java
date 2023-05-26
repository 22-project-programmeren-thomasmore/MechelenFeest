package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;

@Entity
public class OrderTicket {
    @SequenceGenerator(name = "UserSeqGen", sequenceName = "UserSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "UserSeqGen")
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    private int quantity;

    public OrderTicket(Order order, Ticket ticket, int quantity) {
        this.order = order;
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return getTicket().getPrice() * getQuantity();
    }

    public OrderTicket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

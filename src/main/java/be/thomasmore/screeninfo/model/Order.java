package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @SequenceGenerator(name = "UserSeqGen", sequenceName = "UserSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "UserSeqGen")
    @Id
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderTicket> orderTickets = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0;
        List<OrderTicket> orderTickets = getOrderTickets();
        for (OrderTicket orderTicket : orderTickets) {
            sum += orderTicket.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfTickets() {
        return this.orderTickets.size();
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderTicket> getOrderTickets() {
        return orderTickets;
    }

    public void setOrderTickets(List<OrderTicket> orderTickets) {
        this.orderTickets = orderTickets;
    }
}

package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @SequenceGenerator(name = "TicketSeqGen", sequenceName = "TicketSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "TicketSeqGen")
    @Id
    private Integer id;

    private String name;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Festival festival;

    public Ticket(Integer id, String name, double price, Festival festival) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.festival = festival;
    }

    public Ticket() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }
}

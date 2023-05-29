package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @SequenceGenerator(name = "OrderSeqGen", sequenceName = "OrderSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "OrderSeqGen")
    @Id
    private Integer id;

    @ManyToOne
    private EndUser user;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ShoppingCart> cartItems = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0;
        for (ShoppingCart cartItem : cartItems) {
            sum += cartItem.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfTickets() {
        return this.cartItems.size();
    }

    public Order(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }

    public Order(List<ShoppingCart> cartItems, EndUser user) {
        this.cartItems = cartItems;
        this.user = user;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }

    public EndUser getUser() {
        return user;
    }

    public void setUser(EndUser user) {
        this.user = user;
    }
}

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
    private List<ShoppingCart> cartItems = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0;
        for (ShoppingCart cartItem : cartItems) {
            sum += cartItem.getAmount();
        }
        return sum;
    }

    @Transient
    public int getNumberOfTickets() {
        return this.cartItems.size();
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
}

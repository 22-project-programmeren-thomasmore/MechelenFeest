package be.thomasmore.screeninfo.model;

import jakarta.persistence.*;

@Entity
public class ShoppingCart {
    @SequenceGenerator(name = "CartSeqGen", sequenceName = "CartSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "CartSeqGen")
    @Id
    private Integer id;
    private String productName;
    private int productId;
    private int quantity;
    private double totalPrice;


    public ShoppingCart(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = price * quantity;
    }

    public ShoppingCart() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float amount) {
        this.totalPrice = amount;
    }
}
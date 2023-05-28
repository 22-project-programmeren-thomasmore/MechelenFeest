package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.Order;
import be.thomasmore.screeninfo.model.ShoppingCart;
import be.thomasmore.screeninfo.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.ShoppingCart;
import be.thomasmore.screeninfo.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByProductId(int productId);

}

package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByProductIdAndFinished(int productId, boolean b);
    List<ShoppingCart> findAllByFinished(boolean b);

}

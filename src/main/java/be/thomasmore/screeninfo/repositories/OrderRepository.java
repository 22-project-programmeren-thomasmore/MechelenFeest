package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByUser(EndUser user);
}
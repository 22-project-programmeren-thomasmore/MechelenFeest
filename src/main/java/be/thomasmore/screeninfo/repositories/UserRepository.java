package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.EndUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<EndUser, Integer> {
    EndUser findByEmailAddress(String address);
}

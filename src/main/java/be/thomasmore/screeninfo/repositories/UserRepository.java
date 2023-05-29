package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.EndUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<EndUser, Integer> {

    EndUser getByUsername(String username);
}

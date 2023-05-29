package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);
}

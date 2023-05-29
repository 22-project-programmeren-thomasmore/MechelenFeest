package be.thomasmore.screeninfo.config;

import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
        System.out.println("username: " + username);
        EndUser existUser = repo.getByUsername(username);

        if (existUser == null) {
            EndUser newUser = new EndUser();
            newUser.setUsername(username);


            repo.save(newUser);
        }

    }
}

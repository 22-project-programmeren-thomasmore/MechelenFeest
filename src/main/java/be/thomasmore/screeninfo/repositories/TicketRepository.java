package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.EndUser;
import be.thomasmore.screeninfo.model.Festival;
import be.thomasmore.screeninfo.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByFestival(Festival festival);
}

package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.Festival;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends CrudRepository<Festival,Integer> {
    Optional<Festival> findFirstByOrderByIdAsc();
    Optional<Festival> findFirstByOrderByIdDesc();
    Optional<Festival> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Festival> findFirstByIdGreaterThanOrderById(int id);
    Iterable<Festival> findAllByOrderByOnGoingDesc();
    List<Festival> findAll();
}


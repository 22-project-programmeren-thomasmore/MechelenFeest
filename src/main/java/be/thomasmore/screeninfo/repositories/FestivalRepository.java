package be.thomasmore.screeninfo.repositories;

import be.thomasmore.screeninfo.model.Festival;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends CrudRepository<Festival,Integer> {
    Optional<Festival> findFirstByOrderByIdAsc();
    Optional<Festival> findFirstByOrderByIdDesc();
    Optional<Festival> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Festival> findFirstByIdGreaterThanOrderById(int id);
    Iterable<Festival> findAllByOrderByOnGoingDesc();

    List<Festival> findByFestivalNameContainingIgnoreCase(String keyword);


    @Query("SELECT a FROM Festival a " +
            "WHERE ?1 IS NULL OR LOWER(a.festivalName) LIKE LOWER(CONCAT('%',?1,'%'))" +
            "AND (?2 IS NULL OR a.festivalType=?2)")
    List<Festival> findByQuery(String keyword, String festivalType);



}

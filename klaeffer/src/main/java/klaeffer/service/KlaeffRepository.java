package klaeffer.service;

import klaeffer.domain.klaeff.Klaeff;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KlaeffRepository extends CrudRepository<Klaeff,Integer> {
    List<Klaeff> findAll();
    @Query("select count(id) from klaeff")
    Integer size();
}

package samuelmovi.springCliJpa.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {
    Operator save(Operator operator);

    List<Operator> findByLastNameAllIgnoringCase(String lastName);
    List<Operator> findByFirstNameAllIgnoringCase(String firstName);
    List<Operator> findByActive(boolean active);
}

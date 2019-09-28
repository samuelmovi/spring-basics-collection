package samuelmovi.springGuiJpa.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {

    List<Operator> findByLastNameAllIgnoringCase(String lastName);
    List<Operator> findByFirstNameAllIgnoringCase(String firstName);
    List<Operator> findByActive(boolean active);
}

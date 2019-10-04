package samuelmovi.springGuiJpa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import samuelmovi.springGuiJpa.model.Operator;


@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {

    List<Operator> findByLastNameAllIgnoringCase(String lastName);
    List<Operator> findByFirstNameAllIgnoringCase(String firstName);
    List<Operator> findByActive(boolean active);
}

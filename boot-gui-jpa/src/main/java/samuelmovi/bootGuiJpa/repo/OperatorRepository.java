package samuelmovi.bootGuiJpa.repo;

import java.util.List;
import java.util.Optional;

import samuelmovi.bootGuiJpa.model.Operator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {

    List<Operator> findByLastNameAllIgnoringCase(String lastName);
    List<Operator> findByFirstNameAllIgnoringCase(String firstName);
    
    List<Operator> findAllByActive(boolean active);
    List<Operator> findAll();
    Optional<Operator> findById(long id);
    Optional<Operator> findByFirstName(String firstName);
}

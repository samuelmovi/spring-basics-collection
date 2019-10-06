package samuelmovi.bootCliJpa.repo;

import java.util.List;

import samuelmovi.bootCliJpa.model.Operator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {

    List<Operator> findAll();
    List<Operator> findByActive(boolean active);
}

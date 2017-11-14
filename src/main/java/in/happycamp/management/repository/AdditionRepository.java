package in.happycamp.management.repository;

import in.happycamp.management.domain.Addition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface AdditionRepository extends CrudRepository<Addition, Long> {

}

package in.happycamp.management.repository;

import in.happycamp.management.domain.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

}

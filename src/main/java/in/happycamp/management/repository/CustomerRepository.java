package in.happycamp.management.repository;

import in.happycamp.management.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

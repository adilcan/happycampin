package in.happycamp.management.repository;

import in.happycamp.management.domain.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

}

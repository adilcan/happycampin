package in.happycamp.management.repository;

import in.happycamp.management.domain.Food;
import in.happycamp.management.domain.FoodType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

	@Query("SELECT f FROM Food f WHERE f.foodType =:foodType")
	public List<Food> findByFoodType(@Param("foodType") FoodType foodType);

	public Food findByName(String name);
}

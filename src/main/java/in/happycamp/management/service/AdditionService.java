package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.AdditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class AdditionService {

	@Autowired
	private AdditionRepository additionRepository;

	public void save(Addition addition){
		Double price = 0.0;
		for(Food food : addition.getFoods()){
			price = price + food.getPrice();
		}
		addition.setPrice(price);
		additionRepository.save(addition);
	}
}

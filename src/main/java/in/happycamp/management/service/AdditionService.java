package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.AdditionDto;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class AdditionService {

	@Autowired
	private AdditionRepository additionRepository;

	@Autowired
	private FoodRepository foodRepository;

	public void save(AdditionDto additionDto){
		List<Food> foods = new ArrayList<>();

		for(Map.Entry<String, Integer> entry : additionDto.getEatMap().entrySet()){
			if(entry.getValue() != null && entry.getValue() > 0){
				Food e = foodRepository.findByName(entry.getKey());
				e.setQuantity(entry.getValue());
				foods.add(e);
			}
		}

		for(Map.Entry<String, Integer> entry : additionDto.getDrinkMap().entrySet()){
			if(entry.getValue() != null && entry.getValue() > 0){
				Food d = foodRepository.findByName(entry.getKey());
				d.setQuantity(entry.getValue());
				foods.add(d);
			}
		}

		Addition addition = Addition.builder()
				.customer(additionDto.getCustomer())
				.date(new Date())
				.foods(foods)
				.build();

		Double price = 0.0;
		for(Food food : addition.getFoods()){
			price = price + (food.getPrice() * food.getQuantity());
		}
		addition.setPrice(price);
		additionRepository.save(addition);
	}
}

package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.AdditionDto;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
		Map<Food, Integer> foods = new HashMap<>();

		if(additionDto.getEatMap() != null) {
			for (Map.Entry<String, Integer> entry : additionDto.getEatMap().entrySet()) {
				if (entry.getValue() != null && entry.getValue() > 0) {
					foods.put(foodRepository.findByName(entry.getKey()), entry.getValue());
				}
			}
		}

		if(additionDto.getDrinkMap() != null) {
			for (Map.Entry<String, Integer> entry : additionDto.getDrinkMap().entrySet()) {
				if (entry.getValue() != null && entry.getValue() > 0) {
					foods.put(foodRepository.findByName(entry.getKey()), entry.getValue());
				}
			}
		}

		Addition addition = Addition.builder()
				.customer(additionDto.getCustomer())
				.date(new Date())
				.foods(foods)
				.build();

		Double price = 0.0;
		for(Map.Entry<Food, Integer> entry : addition.getFoods().entrySet()){
			price = price + (entry.getKey().getPrice() * entry.getValue());
		}
		addition.setPrice(price);
		additionRepository.save(addition);
	}
}

package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;

	public Map<Food, Integer> getFoodsAsMap() {
		Map<Food, Integer> eatData = new HashMap<>();

		for (Food f : foodRepository.findAll()) {
			eatData.put(f, 0);
		}

		return eatData;
	}


	public Map<Food, Integer> getFoodsAsMapByAddition(Addition addition) {
		Map<Food, Integer> foods = addition.getFoods();
		Map<Food, Integer> eatData = new HashMap<>();

		for(Map.Entry<Food, Integer> entry : foods.entrySet()){
				eatData.put(entry.getKey(), entry.getValue());

		}

		for (Food f : foodRepository.findAll()) {
			if(!eatData.containsKey(f)) {
				eatData.put(f, 0);
			}
		}

		return eatData;
	}

	public Map<String, Integer> getFoodNamesByAddition(Addition addition){
		Map<String, Integer> foodNames = new HashMap<>();
		for(Map.Entry<Food, Integer> food : getFoodsAsMapByAddition(addition).entrySet()){
			foodNames.put(food.getKey().getName(), food.getValue());
		}
		return foodNames;
	}
}
package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.Food;
import in.happycamp.management.domain.FoodType;
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

	public Map<Food, Integer> getAllEats() {
		Map<Food, Integer> eatData = new HashMap<>();

		for (Food f : foodRepository.findByFoodType(FoodType.EAT)) {
			eatData.put(f, 0);
		}

		return eatData;
	}

	public Map<Food, Integer> getAllDrinks() {
		Map<Food, Integer> drinkData = new HashMap<>();

		for (Food f : foodRepository.findByFoodType(FoodType.DRINK)) {
			drinkData.put(f, 0);
		}

		return drinkData;
	}

	public Map<String, Integer> getEatsByAddition(Addition addition) {
		Map<Food, Integer> foods = addition.getFoods();
		Map<String, Integer> eatData = new HashMap<>();

		for(Map.Entry<Food, Integer> entry : foods.entrySet()){
			if(entry.getKey().getFoodType().equals(FoodType.EAT)) {
				eatData.put(entry.getKey().getName(), entry.getValue());
			}
		}

		for (Food f : foodRepository.findByFoodType(FoodType.EAT)) {
			if(!eatData.containsKey(f.getName())) {
				eatData.put(f.getName(), 0);
			}
		}

		return eatData;
	}

	public Map<String, Integer> getDrinksByAddition(Addition addition) {
		Map<Food, Integer> foods = addition.getFoods();
		Map<String, Integer> drinkData = new HashMap<>();

		for(Map.Entry<Food, Integer> entry : foods.entrySet()){
			if(entry.getKey().getFoodType().equals(FoodType.DRINK)) {
				drinkData.put(entry.getKey().getName(), entry.getValue());
			}
		}

		for (Food f : foodRepository.findByFoodType(FoodType.DRINK)) {
			if(!drinkData.containsKey(f.getName())) {
				drinkData.put(f.getName(), 0);
			}
		}

		return drinkData;
	}
}
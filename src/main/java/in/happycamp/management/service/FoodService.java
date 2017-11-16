package in.happycamp.management.service;

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
}
package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.AdditionDto;
import in.happycamp.management.domain.Customer;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Service
public class AdditionService {

	@Autowired
	private AdditionRepository additionRepository;

	@Autowired
	private FoodRepository foodRepository;

	public void create(AdditionDto additionDto) {
		Map<Food, Integer> foods = new HashMap<>();

		if (additionDto.getFoodMap() != null) {
			for (Map.Entry<String, Integer> entry : additionDto.getFoodMap().entrySet()) {
				if (entry.getValue() != null && entry.getValue() > 0) {
					foods.put(foodRepository.findByName(entry.getKey()), entry.getValue());
				}
			}
		}

		Addition addition = Addition.builder().customer(additionDto.getCustomer()).date(new Date()).foods(foods).build();

		Double price = 0.0;
		for (Map.Entry<Food, Integer> entry : addition.getFoods().entrySet()) {
			price = price + (entry.getKey().getPrice() * entry.getValue());
		}
		addition.setPrice(price);
		additionRepository.save(addition);
	}

	public void update(AdditionDto additionDto, Long id) {
		Map<Food, Integer> foods = new HashMap<>();

		if (additionDto.getFoodMap() != null) {
			for (Map.Entry<String, Integer> entry : additionDto.getFoodMap().entrySet()) {
				if (entry.getValue() != null && entry.getValue() > 0) {
					foods.put(foodRepository.findByName(entry.getKey()), entry.getValue());
				}
			}
		}

		Addition addition = additionRepository.findById(id).get();

		Double price = 0.0;
		for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
			price = price + (entry.getKey().getPrice() * entry.getValue());
		}

		if (additionDto.getCustomer() != null) {
			addition.setCustomer(additionDto.getCustomer());
		}

		addition.setFoods(foods);
		addition.setPrice(price);
		additionRepository.save(addition);
	}

	public Map<Food, Integer> getGeneralAddition(Customer customer) {
		Map<Food, Integer> foodMap = new HashMap<>();

		for (Addition a : customer.getAdditions()) {

			for (Map.Entry<Food, Integer> entry : a.getFoods().entrySet()) {

				if (foodMap.containsKey(entry.getKey())) {

					foodMap.replace(entry.getKey(), entry.getValue() + foodMap.get(entry.getKey()));
				}

				else {
					foodMap.put(entry.getKey(), entry.getValue());
				}
			}
		}

		return foodMap;
	}
}

package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.Customer;
import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.FoodRepository;
import in.happycamp.management.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Service
public class CustomerService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FoodRepository foodRepository;

	public List<Food> getGeneralAddition(Customer customer) {
		Map<String, Integer> foodMap = new HashMap<>();
		List<Food> foods = new ArrayList<>();
		int counter = 0;
		for (Addition a : customer.getAdditions()) {

			for (Food f : a.getFoods()) {

				if (foodMap.containsKey(f.getName()) && f.getQuantity() != null) {

					foodMap.replace(f.getName(), f.getQuantity() + 1);
				}

				else {
					foodMap.put(f.getName(), f.getQuantity());
				}
			}
		}

		for(Map.Entry<String, Integer> entry : foodMap.entrySet()){
			Food f = foodRepository.findByName(entry.getKey());
			f.setQuantity(entry.getValue());
			foods.add(f);
		}

		return foods;
	}

	public void save(Customer customer) {
		if (customer.getRoom().getCapacity() <= customer.getRoom().getCustomers().size()) {
			log.warn("Not enough capacity for selected room with id: {}", customer.getRoom().getId());
		}
		else {
			customerRepository.save(customer);
		}
	}

}

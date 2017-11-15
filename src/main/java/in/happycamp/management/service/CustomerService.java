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

import java.util.HashMap;
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

	public void save(Customer customer) {
		if (customer.getRoom().getCapacity() <= customer.getRoom().getCustomers().size()) {
			log.warn("Not enough capacity for selected room with id: {}", customer.getRoom().getId());
		}
		else {
			customerRepository.save(customer);
		}
	}

}

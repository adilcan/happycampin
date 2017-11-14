package in.happycamp.management.service;

import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class CustomerService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	//public void save(Customer customer) {
		//TODO Create @validation for this.
		//if (customer.getRoom().getCapacity() < customer.getRoom().getCustomers().size()) {
			//throw new CapacityException();

	//}

}

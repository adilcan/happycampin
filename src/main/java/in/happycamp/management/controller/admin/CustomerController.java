package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Customer;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import in.happycamp.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Controller
@RequestMapping("/admin/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private AdditionRepository additionRepository;

	@GetMapping("/new")
	public String getCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("rooms", roomRepository.findAll());
		return "admin/customer/createCustomer";
	}

	@PostMapping("/new")
	public String postCustomerForm(@ModelAttribute @Valid Customer customer, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/customer/createCustomer";
		}

		customerRepository.save(customer);
		return "redirect:/admin/customers";
	}

	@GetMapping("")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "admin/customer/listCustomers";
	}

	@GetMapping("/{id}")
	public String showCustomer(@PathVariable("id") Long id, Model model) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if (!customerOptional.isPresent()) {
			log.warn("Customer with id: {} not found.", id);
		}

		model.addAttribute("customer", customerOptional.get());
		model.addAttribute("generalAddition", customerService.getGeneralAddition(customerOptional.get()));
		return "admin/customer/showCustomer";
	}
}

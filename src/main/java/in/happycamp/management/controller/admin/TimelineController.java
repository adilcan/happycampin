package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Customer;
import in.happycamp.management.domain.Room;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Controller
@RequestMapping("/timeline")
public class TimelineController {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("")
	public String getHomePage(Model model) {
		List<Room> rooms = this.roomRepository.findAll();
		model.addAttribute("rooms" , rooms);
		List<Customer> customers = customerRepository.findAll();
		model.addAttribute("customers", customers);

		return "admin/timeline";
	}

}

package in.happycamp.management.controller.admin;

import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("")
	public String getHomePage(Model model) {

		model.addAttribute("rooms", roomRepository.findAll());
		model.addAttribute("customers", customerRepository.findAll());

		return "admin/timeline";
	}

}

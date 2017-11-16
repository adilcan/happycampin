package in.happycamp.management.controller.admin;

import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import in.happycamp.management.service.HomeAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Controller
public class HomeAdminController {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private HomeAdminService homeAdminService;

	@RequestMapping("/")
	public String getHomePage(Model model) {

		model.addAttribute("roomTypeData", homeAdminService.getRoomTypeData());
		model.addAttribute("customerSizeData", homeAdminService.getCustomerSizeData());

		return "admin/dashboard";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "admin/user/login";
	}

}

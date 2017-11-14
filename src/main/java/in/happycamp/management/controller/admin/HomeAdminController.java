package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.RoomType;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Controller
public class HomeAdminController {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/")
	public String getHomePage(Model model) {
		Integer tentCapacity = 7;
		Integer tentSize = roomRepository.findAllByRoomType(RoomType.TENT).size();
		Integer tentAvailable = tentCapacity - tentSize;

		Integer selfTentCapacity = 6;
		Integer selfTentSize = roomRepository.findAllByRoomType(RoomType.SELF_TENT).size();
		Integer selfTentAvailable = selfTentCapacity - selfTentSize;

		Integer bungalowCapacity = 5;
		Integer bungalowSize = roomRepository.findAllByRoomType(RoomType.BUNGALOW).size();
		Integer bungalowAvailable = bungalowCapacity - bungalowSize;

		model.addAttribute("roomTypeData", Arrays.asList(tentSize, tentAvailable, selfTentSize, selfTentAvailable, bungalowSize, bungalowAvailable).toArray());

		Integer customerCapacity = 10;
		Integer customerSize = customerRepository.findAll().size();
		Integer customerAvailable = customerCapacity - customerSize;

		model.addAttribute("customerSizeData", Arrays.asList(customerSize, customerAvailable).toArray());


		return "admin/dashboard";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "admin/user/login";
	}

}

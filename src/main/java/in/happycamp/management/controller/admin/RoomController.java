package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Room;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
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
@RequestMapping("/admin/rooms")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/new")
	public String getRoomForm(Model model) {
		model.addAttribute("room", new Room());
		return "admin/room/createRoom";
	}

	@PostMapping("/new")
	public String postRoomForm(@ModelAttribute @Valid Room room, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/room/createRoom";
		}

		roomRepository.save(room);
		return "redirect:/admin/rooms";
	}

	@GetMapping("")
	public String listRooms(Model model) {
		model.addAttribute("rooms", roomRepository.findAll());
		return "admin/room/listRooms";
	}

	@GetMapping("/{id}")
	public String showRoom(@PathVariable("id") Long id, Model model) {
		Optional<Room> roomOptional = roomRepository.findById(id);
		if (!roomOptional.isPresent()) {
			log.warn("Room with id: {} not found.", id);
		}

		model.addAttribute("room", roomOptional.get());

		return "admin/room/showRoom";
	}

	@GetMapping("/{id}/update")
	public String getRoomUpdate(@PathVariable("id") Long id, Model model) {

		if (!roomRepository.findById(id).isPresent()) {
			log.warn("Room with id: {} not found.", id);
		}

		model.addAttribute("room", roomRepository.findById(id).get());
		return "admin/room/updateRoom";
	}

	@PostMapping("/{id}/update")
	public String postRoomUpdate(@ModelAttribute @Valid Room room, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/room/updateRoom";
		}

		roomRepository.save(room);
		return "redirect:/admin/rooms";
	}


}

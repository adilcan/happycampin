package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.User;
import in.happycamp.management.repository.UserRepository;
import in.happycamp.management.service.UserService;
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
@RequestMapping("/admin/users")
public class UserAdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/new")
	public String getCreateUser(Model model) {
		model.addAttribute("user", new User());
		return "admin/user/createUser";
	}

	@PostMapping("/new")
	public String postCreateUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/user/createUser";
		}

		userService.register(user);
		return "redirect:/admin/users";
	}

	@GetMapping("")
	public String getAllUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "admin/user/listUsers";
	}

	@GetMapping("/{id}")
	public String viewUser(@PathVariable("id") Long id, Model model) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			log.warn("User with id: {} not found.", id);
		}

		model.addAttribute("user", userOptional.get());
		return "admin/user/showUser";
	}
}

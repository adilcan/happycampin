package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.FoodRepository;
import in.happycamp.management.service.AdditionService;
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
@RequestMapping("/admin/additions")
public class AdditionController {

	@Autowired
	AdditionRepository additionRepository;

	@Autowired
	AdditionService additionService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	FoodRepository foodRepository;

	@GetMapping("")
	public String listAdditions(Model model) {
		model.addAttribute("additions", additionRepository.findAll());
		return "admin/addition/listAdditions";
	}

	@GetMapping("/new")
	public String getAdditionForm(Model model) {
		model.addAttribute("addition", new Addition());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("foods", foodRepository.findAll());
		return "admin/addition/createAddition";
	}

	@PostMapping("/new")
	public String postAdditionForm(@ModelAttribute @Valid Addition addition, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/addition/createAddition";
		}

		additionService.save(addition);
		return "redirect:/admin/additions";
	}

	@GetMapping("/{id}")
	public String showAddition(@PathVariable("id") Long id, Model model) {
		Optional<Addition> additionOptional = additionRepository.findById(id);
		if (!additionOptional.isPresent()) {
			log.warn("Addition with id: {} not found.", id);
		}

		model.addAttribute("addition", additionOptional.get());
		return "admin/addition/showAddition";
	}
}


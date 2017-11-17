package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.AdditionDto;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.service.AdditionService;
import in.happycamp.management.service.FoodService;
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
	private AdditionRepository additionRepository;

	@Autowired
	private AdditionService additionService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FoodService foodService;

	@GetMapping("")
	public String listAdditions(Model model) {
		model.addAttribute("additions", additionRepository.findAll());
		return "admin/addition/listAdditions";
	}

	@GetMapping("/new")
	public String getAdditionForm(Model model) {

		model.addAttribute("additionDto", new AdditionDto());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("eatData", foodService.getAllEats());
		model.addAttribute("drinkData", foodService.getAllDrinks());

		return "admin/addition/createAddition";
	}

	@PostMapping("/new")
	public String postAdditionForm(@ModelAttribute @Valid AdditionDto additionDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/addition/createAddition";
		}

		additionService.create(additionDto);
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

	@GetMapping("/{id}/update")
	public String getAdditionUpdateForm(@PathVariable("id") Long id, Model model) {

		if (!additionRepository.findById(id).isPresent()) {
			log.warn("Addition with id: {} not found.", id);
		}

		model.addAttribute("addition", additionRepository.findById(id).get());
		model.addAttribute("additionDto", new AdditionDto());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("eatData", foodService.getEatsByAddition(additionRepository.findById(id).get()));
		model.addAttribute("drinkData", foodService.getDrinksByAddition(additionRepository.findById(id).get()));

		return "admin/addition/updateAddition";
	}

	@PostMapping("/{id}/update")
	public String postAdditionUpdateForm(@PathVariable("id") Long id, @ModelAttribute @Valid AdditionDto additionDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/addition/updateAddition";
		}

		additionService.update(additionDto, id);
		return "redirect:/admin/additions";
	}
}


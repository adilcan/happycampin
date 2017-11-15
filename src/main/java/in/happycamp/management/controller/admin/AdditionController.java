package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.AdditionDto;
import in.happycamp.management.domain.Food;
import in.happycamp.management.domain.FoodType;
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
import java.util.HashMap;
import java.util.Map;
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
	private FoodRepository foodRepository;

	@GetMapping("")
	public String listAdditions(Model model) {
		model.addAttribute("additions", additionRepository.findAll());
		return "admin/addition/listAdditions";
	}

	@GetMapping("/new")
	public String getAdditionForm(Model model) {

		Map<String, Integer> eatData = new HashMap<>();
		Map<String, Integer> drinkData = new HashMap<>();

		for(Food f : foodRepository.findByFoodType(FoodType.EAT)){
			eatData.put(f.getName(), 0);
		}

		for(Food f : foodRepository.findByFoodType(FoodType.DRINK)){
			drinkData.put(f.getName(), 0);
		}

		model.addAttribute("additionDto", new AdditionDto());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("eatData", eatData);
		model.addAttribute("drinkData", drinkData);

		return "admin/addition/createAddition";
	}

	@PostMapping("/new")
	public String postAdditionForm(@ModelAttribute @Valid AdditionDto additionDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/addition/createAddition";
		}

		additionService.save(additionDto);
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


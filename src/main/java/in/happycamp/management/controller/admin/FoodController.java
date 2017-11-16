package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Food;
import in.happycamp.management.repository.FoodRepository;
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
@RequestMapping("/admin/foods")
public class FoodController {

	@Autowired
	private FoodRepository foodRepository;

	@GetMapping("/new")
	public String getFoodFrom(Model model) {
		model.addAttribute("food", new Food());
		return "admin/food/createFood";
	}

	@PostMapping("/new")
	public String postFoodForm(@ModelAttribute @Valid Food food, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/food/createFood";
		}

		foodRepository.save(food);
		return "redirect:/admin/foods";
	}

	@GetMapping("")
	public String listFoods(Model model) {
		model.addAttribute("foods", foodRepository.findAll());
		return "admin/food/listFoods";
	}

	@GetMapping("/{id}")
	public String showFood(@PathVariable("id") Long id, Model model) {
		Optional<Food> foodOptional = foodRepository.findById(id);
		if (!foodOptional.isPresent()) {
			log.warn("Food with id: {} not found.", id);
		}

		model.addAttribute("food", foodOptional.get());

		return "admin/food/showFood";
	}

}

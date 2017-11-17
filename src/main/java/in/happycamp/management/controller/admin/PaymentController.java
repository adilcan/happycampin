package in.happycamp.management.controller.admin;

import in.happycamp.management.domain.Payment;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.PaymentRepository;
import in.happycamp.management.service.PaymentService;
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
@RequestMapping("/admin/payments")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/new")
	public String getPaymentForm(Model model) {
		model.addAttribute("payment", new Payment());
		model.addAttribute("customers", customerRepository.findAll());
		return "admin/payment/createPayment";
	}

	@PostMapping("/new")
	public String postPaymentForm(@ModelAttribute @Valid Payment payment, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/payment/payments";
		}

		paymentService.create(payment);
		return "redirect:/admin/payments";
	}

	@GetMapping("")
	public String listPayments(Model model) {
		model.addAttribute("payments", paymentRepository.findAll());
		return "admin/payment/listPayments";
	}

	@GetMapping("/{id}")
	public String showPayments(@PathVariable("id") Long id, Model model) {
		Optional<Payment> paymentOptional = paymentRepository.findById(id);
		if (!paymentOptional.isPresent()) {
			log.warn("Payment with id: {} not found.", id);
		}

		model.addAttribute("payment", paymentOptional.get());

		return "admin/payment/showPayment";
	}

	@GetMapping("/{id}/update")
	public String getPaymentUpdate(@PathVariable("id") Long id, Model model) {

		if (!paymentRepository.findById(id).isPresent()) {
			log.warn("Payment with id: {} not found.", id);
		}

		model.addAttribute("payment", paymentRepository.findById(id).get());
		model.addAttribute("customers", customerRepository.findAll());
		return "admin/payment/updatePayment";
	}

	@PostMapping("/{id}/update")
	public String postPaymentUpdate(@PathVariable("id") Long id, @ModelAttribute @Valid Payment payment, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/payment/updatePayment";
		}
		paymentService.update(payment, id);
		return "redirect:/admin/payments";
	}
}

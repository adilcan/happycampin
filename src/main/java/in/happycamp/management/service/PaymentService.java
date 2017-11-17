package in.happycamp.management.service;

import in.happycamp.management.domain.Addition;
import in.happycamp.management.domain.Customer;
import in.happycamp.management.domain.Payment;
import in.happycamp.management.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public void create(Payment payment){
		payment.setDate(new Date());
		paymentRepository.save(payment);
	}

	public void update(Payment payment, Long id){
		payment.setDate(paymentRepository.findById(id).get().getDate());
		paymentRepository.save(payment);
	}

	public Map<String, Double> getPaymentInfo(Customer customer) {
		Map<String, Double> paymentInfo = new HashMap();
		double totalAdditionPrice = 0;
		double days = ChronoUnit.DAYS.between(customer.getArrivalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
											  customer.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		double totalRoomPrice = customer.getRoom().getPrice() * days;

		for (Addition addition : customer.getAdditions()) {
			totalAdditionPrice = totalAdditionPrice + addition.getPrice();
		}

		double totalDebt = totalAdditionPrice + totalRoomPrice;

		double paidDebt = 0;
		for (Payment payment : customer.getPayments()) {
			paidDebt = paidDebt + payment.getPrice();
		}

		double remainingDebt = totalDebt - paidDebt;

		paymentInfo.put("paidDebt", paidDebt);
		paymentInfo.put("remainingDebt", remainingDebt);

		return paymentInfo;
	}

}

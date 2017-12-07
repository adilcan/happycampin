package in.happycamp.management;

import in.happycamp.management.domain.*;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.FoodRepository;
import in.happycamp.management.repository.PaymentRepository;
import in.happycamp.management.repository.RoomRepository;
import in.happycamp.management.service.AdditionService;
import in.happycamp.management.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Component
@Slf4j
@AllArgsConstructor
public class SetupInitialData implements CommandLineRunner{

	private final UserService userService;

	private final RoomRepository roomRepository;

	private final CustomerRepository customerRepository;

	private final FoodRepository foodRepository;

	private final AdditionService additionService;

	private final PaymentRepository paymentRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		String DATE_FORMAT = "dd-MM-yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		User user = User.builder()
				.username("hede")
				.password("hede")
				.email("hede@hede.com")
				.role(Role.ADMIN)
				.build();

		userService.register(user);

		Room room0 = Room.builder()
				.code("FIRST")
				.roomType(RoomType.BUNGALOW)
				.capacity(5)
				.price(30.0)
				.build();

		roomRepository.save(room0);

		Room room1 = Room.builder()
				.code("SECOND")
				.roomType(RoomType.TENT)
				.capacity(2)
				.price(30.0)
				.build();

		roomRepository.save(room1);

		Room room2 = Room.builder()
				.code("THIRD")
				.roomType(RoomType.TENT)
				.capacity(4)
				.price(30.0)
				.build();

		roomRepository.save(room2);

		Room room3 = Room.builder()
				.code("FORTH")
				.roomType(RoomType.SELF_TENT)
				.capacity(1)
				.price(30.0)
				.build();

		roomRepository.save(room3);

		Customer customer0 = Customer.builder()
				.fullName("Adil Can")
				.emailAddress("adil@can.com")
				.phoneNumber("5550001122")
				.statusType(StatusType.CHECKOUT)
				.arrivalDate(dateFormat.parse("11-12-2017"))
				.returnDate(dateFormat.parse("18-12-2017"))
				.room(room0)
				.build();

		customerRepository.save(customer0);

		Customer customer1 = Customer.builder()
				.fullName("Joe Strummer")
				.emailAddress("joe@can.com")
				.phoneNumber("5550001122")
				.statusType(StatusType.CHECKOUT)
				.arrivalDate(dateFormat.parse("11-12-2017"))
				.returnDate(dateFormat.parse("18-12-2017"))
				.room(room1)
				.build();

		customerRepository.save(customer1);

		Customer customer2 = Customer.builder()
				.fullName("Ian Curtis")
				.emailAddress("ian@can.com")
				.phoneNumber("5550001122")
				.statusType(StatusType.CHECKOUT)
				.arrivalDate(dateFormat.parse("18-12-2017"))
				.returnDate(dateFormat.parse("25-12-2017"))
				.room(room2)
				.build();

		customerRepository.save(customer2);

		Food food0 = Food.builder()
				.name("Mantar")
				.price(8.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food0);

		Food food1 = Food.builder()
				.name("Falafel")
				.price(4.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food1);

		Food food2 = Food.builder()
				.name("Sebzeli Krep")
				.price(13.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food2);

		Food food3 = Food.builder()
				.name("Pizza")
				.price(15.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food3);

		Food food4 = Food.builder()
				.name("Tuborg 50C")
				.price(13.0)
				.foodType(FoodType.DRINK)
				.build();

		foodRepository.save(food4);

		Food food5 = Food.builder()
				.name("Limonata")
				.price(5.0)
				.foodType(FoodType.DRINK)
				.build();

		foodRepository.save(food5);

		Food food6 = Food.builder()
				.name("Çay")
				.price(0.50)
				.foodType(FoodType.DRINK)
				.build();

		foodRepository.save(food6);

		Map<String, Integer> foodMap0 = new HashMap<>();
		foodMap0.put("Falafel", 1);
		foodMap0.put("Limonata", 2);

		Map<String, Integer> foodMap1 = new HashMap<>();
		foodMap1.put("Pizza", 1);
		foodMap1.put("Tuborg 50C", 3);

		Map<String, Integer> foodMap2 = new HashMap<>();
		foodMap2.put("Çay", 1);
		foodMap2.put("Sebzeli Krep", 2);

		AdditionDto additionDto0 = AdditionDto.builder()
				.customer(customer0)
				.foodMap(foodMap0)
				.build();

		additionService.create(additionDto0);

		AdditionDto additionDto1 = AdditionDto.builder()
				.customer(customer1)
				.foodMap(foodMap1)
				.build();

		additionService.create(additionDto1);

		AdditionDto additionDto2 = AdditionDto.builder()
				.customer(customer2)
				.foodMap(foodMap2)
				.build();

		additionService.create(additionDto2);

		Payment payment0 = Payment.builder()
				.customer(customer0)
				.paymentType(PaymentType.CARD)
				.price(25.0)
				.date(dateFormat.parse("15-12-2017"))
				.build();

		paymentRepository.save(payment0);

		Payment payment1 = Payment.builder()
				.customer(customer1)
				.paymentType(PaymentType.CASH)
				.price(15.0)
				.date(dateFormat.parse("17-12-2017"))
				.build();

		paymentRepository.save(payment1);


	}
}

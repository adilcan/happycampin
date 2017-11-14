package in.happycamp.management;

import in.happycamp.management.domain.*;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.FoodRepository;
import in.happycamp.management.repository.RoomRepository;
import in.happycamp.management.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

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

	private final AdditionRepository additionRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		User user = User.builder()
				.username("hede")
				.password("hede")
				.email("hede@hede.com")
				.role(Role.ADMIN)
				.build();

		userService.register(user);

		Room room = Room.builder()
				.code("ATA")
				.price(33.0)
				.capacity(3)
				.roomType(RoomType.BUNGALOW)
				.build();

		roomRepository.save(room);

		Room room1 = Room.builder()
				.code("LAIK")
				.price(35.0)
				.capacity(2)
				.roomType(RoomType.SELF_TENT)
				.build();

		roomRepository.save(room1);

		Room room2 = Room.builder()
				.code("KEMAL")
				.price(40.0)
				.capacity(4)
				.roomType(RoomType.TENT)
				.build();

		roomRepository.save(room2);

		Customer customer = Customer.builder()
				.fullName("Joe Strummer")
				.returnDate(new Date())
				.arrivalDate(new Date())
				.room(room)
				.build();

		customerRepository.save(customer);

		Customer customer1 = Customer.builder()
				.fullName("Ian Curtis")
				.returnDate(new Date())
				.arrivalDate(new Date())
				.room(room1)
				.build();

		customerRepository.save(customer1);

		Customer customer2 = Customer.builder()
				.fullName("David Bowie")
				.returnDate(new Date())
				.arrivalDate(new Date())
				.room(room2)
				.build();

		customerRepository.save(customer2);

		Food food = Food.builder()
				.name("Tuborg 50C")
				.price(12.0)
				.foodType(FoodType.DRINK)
				.build();

		foodRepository.save(food);

		Food food1 = Food.builder()
				.name("Falafel")
				.price(15.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food1);

		Food food2 = Food.builder()
				.name("Mantar")
				.price(18.0)
				.foodType(FoodType.EAT)
				.build();

		foodRepository.save(food2);

		Addition addition = Addition.builder()
				.foods(Arrays.asList(food, food1))
				.date(new Date())
				.customer(customer)
				.build();

		additionRepository.save(addition);

		Addition addition0 = Addition.builder()
				.foods(Arrays.asList(food, food2, food1))
				.date(new Date())
				.customer(customer)
				.build();

		additionRepository.save(addition0);

		Addition addition1 = Addition.builder()
				.foods(Arrays.asList(food, food1))
				.date(new Date())
				.customer(customer1)
				.build();

		additionRepository.save(addition1);

		Addition addition2 = Addition.builder()
				.foods(Arrays.asList(food1, food2))
				.date(new Date())
				.customer(customer)
				.build();

		additionRepository.save(addition2);




	}
}

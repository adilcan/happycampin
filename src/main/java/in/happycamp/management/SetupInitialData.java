package in.happycamp.management;

import in.happycamp.management.domain.Role;
import in.happycamp.management.domain.User;
import in.happycamp.management.repository.AdditionRepository;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.FoodRepository;
import in.happycamp.management.repository.RoomRepository;
import in.happycamp.management.service.AdditionService;
import in.happycamp.management.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

	}
}

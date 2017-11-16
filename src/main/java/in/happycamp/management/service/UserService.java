package in.happycamp.management.service;

import in.happycamp.management.domain.User;
import in.happycamp.management.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Slf4j
@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public void register(User user){
		User u = User.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.role(user.getRole())
				.password(passwordEncoder.encode(user.getPassword()))
				.build();

		userRepository.save(u);

	}

	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

}

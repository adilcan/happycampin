package in.happycamp.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.util.Map;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdditionDto {

	@ManyToOne
	private Customer customer;

	private Map<String, Integer> eatMap;

	private Map<String, Integer> drinkMap;


}

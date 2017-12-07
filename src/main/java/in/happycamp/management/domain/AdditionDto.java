package in.happycamp.management.domain;

import lombok.*;

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
@Builder
public class AdditionDto {

	private Customer customer;

	private Map<String, Integer> foodMap;

}

package in.happycamp.management.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Food extends BaseEntity {

	private String name;

	private Double price;

	@Enumerated(EnumType.STRING)
	private FoodType foodType;

	private Integer quantity;

}

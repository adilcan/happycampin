package in.happycamp.management.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

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
public class Addition extends BaseEntity {

	@ManyToMany
	private List<Food> foods;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@ManyToOne
	private Customer customer;

	private Double price;

}

package in.happycamp.management.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
public class Customer extends BaseEntity {

	private String fullName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	@ManyToOne
	private Room room;

	@OneToOne
	private Bill bill;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Addition> additions;
}

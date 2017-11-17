package in.happycamp.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

	private String emailAddress;

	private String phoneNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	@ManyToOne
	private Room room;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Payment> payments;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Addition> additions;

	private StatusType statusType;

}

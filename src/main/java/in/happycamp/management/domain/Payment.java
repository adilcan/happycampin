package in.happycamp.management.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

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
public class Payment extends BaseEntity {

	@ManyToOne
	private Customer customer;

	private PaymentType paymentType;

	private Double price;

	private Date date;

}

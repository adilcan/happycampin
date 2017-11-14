package in.happycamp.management.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
public class Bill extends BaseEntity {

	@OneToOne
	private Customer customer;

	private PaymentType paymentTypes;

	private boolean isPaid = false;

	private Double totalPrice;

}

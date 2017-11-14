package in.happycamp.management.domain;

import lombok.*;

import javax.persistence.*;
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
public class Room extends BaseEntity {

	private String code;

	private Integer capacity;

	private Double price;

	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	private boolean isCleaned;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Customer> customers;

}

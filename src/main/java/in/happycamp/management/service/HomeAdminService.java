package in.happycamp.management.service;

import in.happycamp.management.domain.RoomType;
import in.happycamp.management.repository.CustomerRepository;
import in.happycamp.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Service
public class HomeAdminService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Object[] getRoomTypeData() {

		Integer tentCapacity = 7;
		Integer tentSize = roomRepository.findAllByRoomType(RoomType.TENT).size();
		Integer tentAvailable = tentCapacity - tentSize;

		Integer selfTentCapacity = 6;
		Integer selfTentSize = roomRepository.findAllByRoomType(RoomType.SELF_TENT).size();
		Integer selfTentAvailable = selfTentCapacity - selfTentSize;

		Integer bungalowCapacity = 5;
		Integer bungalowSize = roomRepository.findAllByRoomType(RoomType.BUNGALOW).size();
		Integer bungalowAvailable = bungalowCapacity - bungalowSize;
		return Arrays.asList(tentSize, tentAvailable, selfTentSize, selfTentAvailable, bungalowSize, bungalowAvailable).toArray();
	}

	public Object[] getCustomerSizeData() {

		Integer customerCapacity = 10;
		Integer customerSize = customerRepository.findAll().size();
		Integer customerAvailable = customerCapacity - customerSize;

		return Arrays.asList(customerSize, customerAvailable).toArray();
	}

}

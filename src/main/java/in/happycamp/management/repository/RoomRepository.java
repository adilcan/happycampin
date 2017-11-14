package in.happycamp.management.repository;

import in.happycamp.management.domain.Room;
import in.happycamp.management.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on November, 2017
 *
 * @author adilcan
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT r FROM Room r WHERE roomType = :roomType")
	List<Room> findAllByRoomType(@Param("roomType") RoomType roomType);
}

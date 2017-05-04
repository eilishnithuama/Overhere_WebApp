package project.rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.rest.Entities.UserEvent;

public interface UserEventRepository extends JpaRepository<UserEvent,Long> {

	List<UserEvent> findByUserid(int user_id);

}

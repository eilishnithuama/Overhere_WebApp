package project.rest.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import project.rest.Entities.Fall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FallRepository extends JpaRepository<Fall,Long> {
	Fall findById(int id);
	List<Fall> findByUserid(int userid);

}

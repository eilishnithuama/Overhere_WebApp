package project.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import project.rest.Entities.Carer;

public interface CarerRepository extends JpaRepository<Carer,Long> {

	Carer findByName(String username);

}

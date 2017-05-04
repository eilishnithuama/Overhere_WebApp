package project.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import project.rest.Entities.Heart;

public interface HeartRateRepository extends JpaRepository<Heart,Long> {

}

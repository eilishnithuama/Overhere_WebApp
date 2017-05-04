package project.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import project.rest.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

	UserEntity findByUsername(String username);

}

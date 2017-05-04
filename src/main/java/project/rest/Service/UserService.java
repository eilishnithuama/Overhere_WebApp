package project.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.rest.Entities.UserEntity;
import project.rest.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;

	public Iterable<UserEntity> findAll() {
		return userRepo.findAll();
	}
	
	public UserEntity findByUsername(String username){
		return userRepo.findByUsername(username);
	}

	public UserEntity save(UserEntity user) {
		return userRepo.save(user);
	}

	public UserEntity findByUserId(int user_id) {
		Iterable<UserEntity> list=  userRepo.findAll();
		for(UserEntity ue : list){
			if(ue.getUser_id() == user_id){
				return ue;
			}
		}
		
		return null;
	}
}


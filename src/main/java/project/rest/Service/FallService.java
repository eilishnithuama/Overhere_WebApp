package project.rest.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.rest.Entities.Fall;
import project.rest.Entities.UserEntity;
import project.rest.Repository.FallRepository;

@Service
public class FallService {
	
	@Autowired
	FallRepository fallRepo;
	int size = 0;

	public Fall findById(int id) {
		return fallRepo.findById(id);
	}

	public Fall findLatest(int userid) {
			List<Fall> fullList = findAllByUserid(userid);
			if(fullList.size() == 0)
				return new Fall();
			else
				return fullList.get(fullList.size()-1);
	}

	public Iterable<Fall> findAll() {
		return fallRepo.findAll();
	}
	
	public List<Fall> findAllByUserid(int userid){
		return fallRepo.findByUserid(userid);
	}

	public void save(Fall fall) {
		fallRepo.save(fall);
		
	}
}

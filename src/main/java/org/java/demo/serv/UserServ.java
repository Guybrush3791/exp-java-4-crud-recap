package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.User;
import org.java.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServ {

	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}
	public User save(User user) {
		
		return userRepo.save(user);
	}
}

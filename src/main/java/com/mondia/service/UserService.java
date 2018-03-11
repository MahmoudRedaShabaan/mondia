package com.mondia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mondia.models.User;
import com.mondia.repositry.UserRepository;

@Service
public class UserService {

	private final UserRepository repo;
	
	@Autowired
    public UserService(UserRepository repo) {
		this.repo = repo;
	}

	

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

}

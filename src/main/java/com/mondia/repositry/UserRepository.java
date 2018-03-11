package com.mondia.repositry;

import org.springframework.data.repository.CrudRepository;

import com.mondia.models.User;




public interface UserRepository extends CrudRepository <User, Long> {

	User findByUsername(String userName);
}

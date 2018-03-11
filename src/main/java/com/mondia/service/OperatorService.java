package com.mondia.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondia.models.Operator;
import com.mondia.models.User;
import com.mondia.repositry.OperatorRepository;
import com.mondia.repositry.UserRepository;

@Service
@Transactional
public class OperatorService {

	private final OperatorRepository operatorRepository;
	private final UserRepository userRepository;
	

	@Autowired
	public OperatorService(OperatorRepository operatorRepository,UserRepository userRepository) {
		this.operatorRepository = operatorRepository;
		this.userRepository=userRepository;

	}

	public Operator addOperator(Operator Operator) {
		return operatorRepository.save(Operator);
	}

	public Operator editOperator(Operator Operator) {
		return operatorRepository.save(Operator);
	}

	public void deleteOperator(Long id) {
		operatorRepository.delete(id);
	}

	public Operator getOperatorById(Long id) {
		return operatorRepository.findOne(id);
	}

	public Iterable<Operator> listOperator() {
		return operatorRepository.findAll();
	}

	public User getuser(Long id) {
		return userRepository.findOne(id);
	}
	

}

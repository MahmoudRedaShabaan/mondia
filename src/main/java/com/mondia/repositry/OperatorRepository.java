package com.mondia.repositry;

import org.springframework.data.repository.CrudRepository;

import com.mondia.models.Operator;


public interface OperatorRepository extends CrudRepository <Operator, Long> {

}

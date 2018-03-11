package com.mondia.repositry;

import org.springframework.data.repository.CrudRepository;

import com.mondia.models.Services;


public interface ServiceRepository extends CrudRepository <Services, Long> {

}

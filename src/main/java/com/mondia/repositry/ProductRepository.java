package com.mondia.repositry;

import org.springframework.data.repository.CrudRepository;

import com.mondia.models.Product;


public interface ProductRepository extends CrudRepository <Product, Long> {

}

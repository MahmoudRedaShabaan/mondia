package com.mondia.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondia.models.Product;
import com.mondia.repositry.ProductRepository;

@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;

	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product editProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}

	public Product getProductById(Long id) {
		return productRepository.findOne(id);
	}

	public Iterable<Product> listProduct() {
		return productRepository.findAll();
	}
	

}

package com.mondia.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.models.Product;
import com.mondia.repositry.ProductRepository;
import com.mondia.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Operations on Product (List/View/Add/Delete/Edit)")
public class ProductResource {

	@Autowired
	ProductService					productService;

	private final ProductRepository	productRepository;

	public ProductResource(ProductRepository	productRepository) {
		this.productRepository = productRepository;
	}

	@PostMapping(path = "product", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "add a new product", response = Product.class)
	public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (product.getId() != null) {
			return new ResponseEntity<>("a new Product can not has id", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			Product productResponse = productService.addProduct(product);
			return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
		}
	}

	@PutMapping(path = "product", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "edit product", response = Product.class)
	public ResponseEntity<?> editProduct(@Valid @RequestBody Product product) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (productRepository.findOne(product.getId()) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			Product productResponse = productService.editProduct(product);
			return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "product/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "delete product", response = Product.class)
	public ResponseEntity<?> editProduct(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (productRepository.findOne(id) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping(path = "product/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "get product by id", response = Product.class)
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Product responseProduct = productService.getProductById(id);
		if (responseProduct == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(responseProduct, HttpStatus.OK);
		}
	}

	@GetMapping(path = "product", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "list all products", response = Product.class)
	public ResponseEntity<?> listProduct() {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Iterable<Product> productResponse = productService.listProduct();
		return new ResponseEntity<>(productResponse, HttpStatus.OK);

	}
}

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

import com.mondia.models.Operator;
import com.mondia.models.User;
import com.mondia.repositry.OperatorRepository;
import com.mondia.service.OperatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Operations on Operator (List/View/Add/Delete/Edit)")
public class OperatorResource {

	@Autowired
	OperatorService				operatorService;

	private final OperatorRepository	operatorRepository;

	public OperatorResource(OperatorRepository	operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	@PostMapping(path = "operator", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "add a new operator", response = Operator.class)
	public ResponseEntity<?> addoperator(@Valid @RequestBody Operator operator) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (operator.getId() != null) {
			return new ResponseEntity<>("a new Operator can not has id", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			Operator operatorResponse = operatorService.addOperator(operator);
			return new ResponseEntity<>(operatorResponse, HttpStatus.CREATED);
		}
	}

	@PutMapping(path = "operator", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "edit operator", response = Operator.class)
	public ResponseEntity<?> editOperator(@Valid @RequestBody Operator operator) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (operatorRepository.findOne(operator.getId()) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			Operator operatorResponse = operatorService.editOperator(operator);
			return new ResponseEntity<>(operatorResponse, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "operator/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "delete operator", response = Operator.class)
	public ResponseEntity<?> editOperator(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (operatorRepository.findOne(id) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			operatorService.deleteOperator(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping(path = "operator/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "get operator by id", response = Operator.class)
	public ResponseEntity<?> getOperator(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Operator responseoperator = operatorService.getOperatorById(id);
		if (responseoperator == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(responseoperator, HttpStatus.OK);
		}
	}

	@GetMapping(path = "operator", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "list all operators", response = Operator.class)
	public ResponseEntity<?> listOperator() {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Iterable<Operator> operatorResponse = operatorService.listOperator();
		return new ResponseEntity<>(operatorResponse, HttpStatus.OK);

	}
	
	@GetMapping(path = "operator/user/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "get operator by id", response = User.class)
	public ResponseEntity<?> getOperatoruser(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		User user = operatorService.getuser(id);
		if (user == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
}

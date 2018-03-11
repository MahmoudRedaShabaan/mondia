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

import com.mondia.models.Services;
import com.mondia.repositry.OperatorRepository;
import com.mondia.repositry.ServiceRepository;
import com.mondia.service.ServiceService;
import com.mondia.web.rest.error.CustomParameterizedException;
import com.mondia.web.rest.error.ErrorConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Operations on Brand (List/View/Add/Delete/Edit)")
public class ServiceResource {

	@Autowired
	ServiceService						serviceService;

	private final ServiceRepository		serviceRepository;
	private final OperatorRepository	operatorRepository;

	public ServiceResource(ServiceRepository serviceRepository, OperatorRepository operatorRepository) {
		this.serviceRepository = serviceRepository;
		this.operatorRepository = operatorRepository;
	}

	@PostMapping(path = "services", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "add a new Service", response = Services.class)
	public ResponseEntity<?> addServices(@Valid @RequestBody Services services) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (services.getId() != null) {
			return new ResponseEntity<>("a new service can not has id", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			String operatorName = operatorRepository.findOne(services.getOperatorId()).getName();
			if (operatorName.equals("Orange Egypt")&&services.getOperatorserviceId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_SERVICE_ID_MANDATORY);
			}
			if (operatorName.equals("Etisalat Egypt")&&services.getOperatorpackageId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_PACKAGE_ID_MANDATORY);
			}
			if (operatorName.equals("Vodafone Egypt")&&services.getOperatorserviceId()==null&&services.getOperatorpackageId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_PACKAGE_AND_SERVICE_ID_MANDATORY);
			}
			Services ServicesResponse = serviceService.addService(services);
			return new ResponseEntity<>(ServicesResponse, HttpStatus.CREATED);
		}
	}

	@PutMapping(path = "services", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "edit Services", response = Services.class)
	public ResponseEntity<?> editService(@Valid @RequestBody Services services) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (serviceRepository.findOne(services.getId()) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			String operatorName = operatorRepository.findOne(services.getOperatorId()).getName();
			if (operatorName.equals("Orange Egypt")&&services.getOperatorserviceId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_SERVICE_ID_MANDATORY);
			}
			if (operatorName.equals("Etisalat Egypt")&&services.getOperatorpackageId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_PACKAGE_ID_MANDATORY);
			}
			if (operatorName.equals("Vodafone Egypt")&&services.getOperatorserviceId()==null&&services.getOperatorpackageId()==null) {
				throw new CustomParameterizedException(ErrorConstants.SERVICE.OPERATOR_PACKAGE_AND_SERVICE_ID_MANDATORY);
			}
			Services ServicesResponse = serviceService.editService(services);
			return new ResponseEntity<>(ServicesResponse, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "services/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "delete Services", response = Services.class)
	public ResponseEntity<?> deleteService(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		if (serviceRepository.findOne(id) == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			serviceService.deleteService(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping(path = "services/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "get Services by id", response = Services.class)
	public ResponseEntity<?> getService(@PathVariable Long id) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Services responseServices = serviceService.getServiceById(id);
		if (responseServices == null) {
			return new ResponseEntity<>("id not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(responseServices, HttpStatus.OK);
		}
	}

	@GetMapping(path = "services", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiOperation(value = "list all Servicess", response = Services.class)
	public ResponseEntity<?> listServices() {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		Iterable<Services> ServicesResponse = serviceService.listService();
		return new ResponseEntity<>(ServicesResponse, HttpStatus.OK);

	}
}

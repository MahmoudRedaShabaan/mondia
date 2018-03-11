package com.mondia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mondia.models.Services;
import com.mondia.models.Type;
import com.mondia.repositry.OperatorRepository;
import com.mondia.repositry.ProductRepository;
import com.mondia.repositry.ServiceRepository;
import com.mondia.repositry.TypeRepository;

@Service
@Transactional
public class ServiceService {

	private final ServiceRepository serviceRepository;
	private final TypeRepository typeRepository;
	private final ProductRepository productRepository;
	private final OperatorRepository operatorRepository;
	
	

	@Autowired
	public ServiceService(ServiceRepository serviceRepository,TypeRepository typeRepository,ProductRepository productRepository,OperatorRepository operatorRepository) {
		this.serviceRepository = serviceRepository;
		this.typeRepository =typeRepository;
		this.productRepository=productRepository;
		this.operatorRepository =operatorRepository;
	}

	public Services addService(Services Service) {
		return serviceRepository.save(Service);
	}

	public Services editService(Services Service) {
		return serviceRepository.save(Service);
	}

	public void deleteService(Long id) {
		serviceRepository.delete(id);
	}

	public Services getServiceById(Long id) {
		Services services=serviceRepository.findOne(id);
		services.setTypeName(typeRepository.findOne(services.getTypeId()).getName());
		services.setProductName(productRepository.findOne(services.getProductId()).getName());
		services.setOperatorName(operatorRepository.findOne(services.getOperatorId()).getName());
		return services;
	}

	public Iterable<Services> listService() {
		
		Iterable<Services> services=serviceRepository.findAll();
		for(Services s: services){
			s.setTypeName(typeRepository.findOne(s.getTypeId()).getName());
			s.setProductName(productRepository.findOne(s.getProductId()).getName());
			s.setOperatorName(operatorRepository.findOne(s.getOperatorId()).getName());
		}
		return services;
	}
	

}

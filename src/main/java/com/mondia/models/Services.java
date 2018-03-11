package com.mondia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "service")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@NotNull
	@Column(name = "name")
	private String	name;
	
	@NotNull
	@Column(name = "type_id")
	private Long	typeId;
	
	@NotNull
	@Column(name = "operator_id")
	private Long	operatorId;
	
	@Column(name = "operatorservice_id")
	private Long	operatorserviceId;
	
	@Column(name = "operatorpackage_id")
	private Long	operatorpackageId;
	
	@NotNull
	@Column(name = "product_id")
	private Long	productId;
	
	private transient String typeName;
	private transient String productName;
	private transient String operatorName;
	
}

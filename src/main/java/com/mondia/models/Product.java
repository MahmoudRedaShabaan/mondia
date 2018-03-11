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
@Table(name = "product")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@NotNull
	@Column(name = "name")
	private String	name;
	
	@Column(name = "description")
	private String	description;
	
	@NotNull
	@Column(name = "min_price")
	private Double	minPrice;
	
	@NotNull
	@Column(name = "max_price")
	private Double	maxPrice;

}

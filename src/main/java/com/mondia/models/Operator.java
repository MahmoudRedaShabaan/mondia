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
@Table(name = "operator")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@NotNull
	@Column(name = "name")
	private String	name;
	
	@NotNull
	@Column(name = "country")
	private String	country;

}

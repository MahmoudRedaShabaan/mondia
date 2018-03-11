package com.mondia.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "iuser")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class User {

	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	@Column(name = "username")
	private String	username;
	
	@Column(name = "password")
	private String	password;
	

//    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    private List<Role> roles;
    
    User() {}

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
    //    this.roles = roles;
    }

}

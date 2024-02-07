package com.example.kishore.entity;


import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
      
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String firstname;
	private String lastname;
	
	
	private String address;
	
	private String contact;
	private String email;
	
	private String gender;
	
	@OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Orders> orders = new ArrayList<>();
	
	@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	private List<Cartitem> cart = new ArrayList<>();
	private String cartItems;
	
	private String username;
	private String password;
	
	}

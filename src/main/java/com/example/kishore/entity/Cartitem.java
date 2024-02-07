package com.example.kishore.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"handler","customer"})
public class Cartitem {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productid")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerid")
	private Customer customer;
	
	private int count;
	
}

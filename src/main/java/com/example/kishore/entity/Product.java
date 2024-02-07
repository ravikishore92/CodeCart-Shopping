package com.example.kishore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"handler","productOrders"})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String features;
	private Integer price;
	private Integer count;
	private String image;
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Orders> productOrders = new ArrayList<>();
}

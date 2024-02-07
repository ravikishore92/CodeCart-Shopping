package com.example.kishore.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"handler","customer"})
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private Integer price;
	private Integer count;
	private Date orderedDate;
	private Date deliveryDate;
}

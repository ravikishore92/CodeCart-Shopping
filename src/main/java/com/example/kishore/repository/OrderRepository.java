package com.example.kishore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kishore.entity.Customer;
import com.example.kishore.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
	
	void deleteByCustomer(Customer customer);

}

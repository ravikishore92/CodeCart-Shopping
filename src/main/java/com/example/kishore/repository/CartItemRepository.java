package com.example.kishore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kishore.entity.Cartitem;
import com.example.kishore.entity.Customer;

@Repository
public interface CartItemRepository extends JpaRepository<Cartitem, Long>{
	
     List<Cartitem> findByCustomer(Customer customer);
	
	void deleteByCustomer(Customer customer);
}

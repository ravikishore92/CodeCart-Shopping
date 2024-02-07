package com.example.kishore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kishore.entity.Product;
import com.example.kishore.repository.ProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("getall")
	public ResponseEntity<?> getAll(){
		System.out.println("in all");
		return ResponseEntity.ok().body(productRepo.findAll());
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		System.out.println("in product");
		productRepo.save(product);
		
		return ResponseEntity.ok().body("product added");
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		Optional<Product> p=productRepo.findById(id);
		productRepo.delete(p.get());
		
		return ResponseEntity.ok().body("deleted");
	}
}


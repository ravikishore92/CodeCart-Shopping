package com.example.kishore.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kishore.entity.Cartitem;
import com.example.kishore.entity.Customer;
import com.example.kishore.entity.Orders;
import com.example.kishore.entity.Product;
import com.example.kishore.model.LoginCredentials;
import com.example.kishore.model.LoginStatus;
import com.example.kishore.model.UpdateNames;
import com.example.kishore.repository.CartItemRepository;
import com.example.kishore.repository.CustomerRepository;
import com.example.kishore.repository.OrderRepository;
import com.example.kishore.repository.ProductRepository;

import jakarta.transaction.Transactional;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private String userName="";
	
	@PostMapping("")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
	{
		return ResponseEntity.ok().body("Customer added Successfully");
	}
	@PostMapping("login")
	public ResponseEntity<?>customerLogin(@RequestBody LoginCredentials loginCredentials){
		
		Customer customer = customerRepo.findByUsername(loginCredentials.getUsername());

		
		if(customer == null)
		{
			return ResponseEntity.badRequest().body(new LoginStatus("Invalid Credentials","error"));
		}
		else
		{    
			if((customer.getPassword()).equals(loginCredentials.getPassword()))
			{
				userName=customer.getUsername();
				return ResponseEntity.ok().body(new LoginStatus("Login Successfully","success"));	
			}
		}
		
		 return ResponseEntity.badRequest().body(new LoginStatus("Login Successfully","success"));
	}
	
	@Transactional
	@PutMapping("updatepass/{email}")
	public ResponseEntity<?> passwordUpdate(@PathVariable("email") String email,@RequestBody String password)
	{
		Customer customer = customerRepo.findByEmail(email);
		
		customer.setPassword(password);
		
		customerRepo.save(customer);
		return ResponseEntity.ok().body("password updated");
	}
	
	@GetMapping("profile")
	public ResponseEntity<?> getProfile(){
	
		Customer c = customerRepo.findByUsername(userName);
		System.out.println(c);
		return ResponseEntity.ok().body(c);
	}
	
	
	@GetMapping("addtocart/{productid}")
	public ResponseEntity<?> addProductToCart(@PathVariable("productid") Long productid) {
		
		if(userName==null) {
			System.out.println("ok not null");
			return ResponseEntity.badRequest().body("Please login first!");
		}
		
		Customer c = customerRepo.findByUsername(userName);
		Optional<Product> p = productRepository.findById(productid);
		System.out.println(c.getEmail());
		System.out.println(p.get().getName());
		List<Cartitem> items = c.getCart();
		Optional<Cartitem> item = items.stream()
				.filter(ci -> ci.getProduct().getId().equals(productid))
				.findAny();
		
		if(item.isPresent())
		{
			item.get().setCount(item.get().getCount()+1);
		}
		else
		{
			Cartitem cart = new Cartitem();
			cart.setCount(1);
			cart.setCustomer(c);
			cart.setProduct(p.get());
			
		    cartItemRepository.save(cart);
		}
		customerRepo.save(c);
		System.out.println(c.getCart());
		return ResponseEntity.ok().body("Added Successfully");
	}
	
	@GetMapping("getCartItems")
	public ResponseEntity<?>getCartItems()
	{
		if(userName==null) {
			System.out.println("ok null");
			return ResponseEntity.badRequest().body("Please login first!");
		}
		Customer c = customerRepo.findByUsername(userName);
	   return ResponseEntity.ok().body(c.getCart()); 
	}
	
	@Transactional
	@DeleteMapping("deletecartitem/{cartid}")
	public ResponseEntity<?> deleteCartItem(@PathVariable("cartid") Long cartid)
	{
		cartItemRepository.deleteById(cartid);
		return ResponseEntity.ok().body("Cart Item deleted successfully");
	}

	@GetMapping("placeorder")
	@Transactional
	public ResponseEntity<?> placeOrder()
	{
		Customer c = customerRepo.findByUsername(userName);
		
		List<Orders> orders = new ArrayList<>();		
		for(Cartitem item : c.getCart())
		{
		     	Orders order = Orders.builder()
		     			    		 .customer(c)
		     			    		 .product(item.getProduct())
		     			    		 .price(item.getCount() * item.getProduct().getPrice())
		     			    		 .count(item.getCount())
		     			    		 .orderedDate(new Date(System.currentTimeMillis()))
		     			    		 .deliveryDate(new Date(System.currentTimeMillis()+ 5*24*60*60*1000))
		     			    		 .build();
		     	orders.add(order);
		}
		orderRepository.saveAll(orders);
		cartItemRepository.deleteByCustomer(c);
		return ResponseEntity.ok().body("Items placed Successfully");
	}
	
	@GetMapping("getorders")
	public ResponseEntity<?> getOrders()
	{
		Customer c = customerRepo.findByUsername(userName);
		if(c == null)
		{
			return ResponseEntity.badRequest().body("null");
		}
		
		return ResponseEntity.ok().body(c.getOrders());
	}
	@Transactional
	@DeleteMapping("cancelorder/{id}")
	public ResponseEntity<?> cancelOrder(@PathVariable("id") Long id)
	{   
		System.out.println("in cancle up");
		orderRepository.deleteById(id);
		System.out.println("in cancle");
		return ResponseEntity.ok().body("cancled ");
	}
	
	@Transactional
	@PutMapping("updatename")
	public ResponseEntity<?> updateName(@RequestBody UpdateNames names)
	{
		Customer c = customerRepo.findByUsername(userName);
		c.setFirstname(names.getFirstname());
		c.setLastname(names.getLastname());
		customerRepo.save(c);
		
		return ResponseEntity.ok().body("data Updated");
	}
	
	@Transactional
	@PutMapping("updateemail")
	public ResponseEntity<?> emailUpdate(@RequestBody String email)
	{
		Customer c = customerRepo.findByUsername(userName);
		c.setEmail(email);
		customerRepo.save(c);
		
		return ResponseEntity.ok().body("data Updated");
	}
	
	@Transactional
	@PutMapping("updatemobile")
	public ResponseEntity<?> mobileUpdate(@RequestBody String mobile)
	{
		Customer c = customerRepo.findByUsername(userName);
		c.setContact(mobile);
		customerRepo.save(c);
		
		return ResponseEntity.ok().body("data Updated");
	}

}

package com.example.kishore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String password;
}

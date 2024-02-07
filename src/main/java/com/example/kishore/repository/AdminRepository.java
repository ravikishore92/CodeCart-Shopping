package com.example.kishore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kishore.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByUsername(String username);
}

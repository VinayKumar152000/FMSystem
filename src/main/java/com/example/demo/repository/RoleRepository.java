package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
	List<Role> findByNameIn(Collection<String> roles);
}

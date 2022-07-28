package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.RoleBo;
import com.example.demo.payload.RolePayload;
import com.example.demo.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	RoleService service;

	@GetMapping
	public List<RoleBo> getAllRoles() {
		return service.getAllRoles();
	}

	@GetMapping("/{id}")
	public RoleBo getSingleRole(@PathVariable(value = "id") int userId) {
		return service.getRoleById(userId);
	}

	@PostMapping
	public RoleBo createRole(@RequestBody RolePayload role) {
		return service.createRole(role);
	}

	@PutMapping("/{id}")
	public RoleBo updateRole(@RequestBody RolePayload role, @PathVariable("id") int roleId) {
		return service.updateRole(role, roleId);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable("id") int roleId) {
		service.deleteRole(roleId);
	}
}

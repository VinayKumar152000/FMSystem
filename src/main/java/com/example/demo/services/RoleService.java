package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.bo.RoleBo;
import com.example.demo.domain.Role;
import com.example.demo.exceptions.InvalidInfoException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payload.RolePayload;
import com.example.demo.repository.RoleRepository;
import java.util.*;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	public List<RoleBo> getAllRoles() {

		List<Role> list = this.repo.findAll();
		List<RoleBo> listbo = new ArrayList<>();

		for (Role role : list) {
			RoleBo rolebo = RoletoRoleBo(role);
			listbo.add(rolebo);
		}

		return listbo;
	}

	public RoleBo getRoleById(int roleId) {
		Optional<Role> optional = this.repo.findById(roleId);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Role", "roleId", roleId);
		}

		Role role = optional.get();
		RoleBo rolebo = RoletoRoleBo(role);
		return rolebo;

	}

	public RoleBo createRole(RolePayload role) {

		if (role.getName().equals("")) {
			throw new InvalidInfoException("Please Enter a Valid Role Info", HttpStatus.BAD_REQUEST);
		}

		Role r = new Role();
		r.setName(role.getName());
		this.repo.save(r);
		Role roles = this.repo.findByName(role.getName());
		RoleBo rolebo = RoletoRoleBo(roles);

		return rolebo;
	}

	public RoleBo updateRole(RolePayload role, int roleId) {
		Optional<Role> optional = this.repo.findById(roleId);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Role", "roleId", roleId);
		}
		if (role.getName().equals("")) {
			throw new InvalidInfoException("Please Enter a Valid Role Info", HttpStatus.BAD_REQUEST);
		}
		Role existingRole = optional.get();
		existingRole.setName(role.getName());

		this.repo.save(existingRole);
		RoleBo rolebo = RoletoRoleBo(existingRole);

		return rolebo;
	}

	public String deleteRole(int roleId) {
		Optional<Role> optional = this.repo.findById(roleId);

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Role", "roleId", roleId);
		}

		Role existingRole = optional.get();
		this.repo.delete(existingRole);
		return "Roleis Deleted with " + roleId;
	}

	public RoleBo RoletoRoleBo(Role role) {
		RoleBo rolebo = this.modelMapper.map(role, RoleBo.class);
		return rolebo;
	}
}
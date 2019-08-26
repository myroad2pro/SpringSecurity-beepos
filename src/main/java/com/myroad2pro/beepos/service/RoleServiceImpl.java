package com.myroad2pro.beepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroad2pro.beepos.domain.Role;
import com.myroad2pro.beepos.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findOne(String role) {
		return roleRepository.findByName(role); 
	}

}

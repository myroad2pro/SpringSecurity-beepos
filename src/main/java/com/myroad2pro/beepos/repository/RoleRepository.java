package com.myroad2pro.beepos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByName(String name);
}

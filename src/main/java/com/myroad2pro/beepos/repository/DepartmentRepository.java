package com.myroad2pro.beepos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	Department findByName(String name);
	
	List<Department> findByNameContaining(String term);
	
	List<Department> findByBranchId(Integer branchId);
}

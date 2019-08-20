package com.myroad2pro.beepos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Integer>{
	Branch findByName(String name);
}

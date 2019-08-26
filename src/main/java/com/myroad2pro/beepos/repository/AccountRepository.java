package com.myroad2pro.beepos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	Account findByEmail(String term);
	
	List<Account> findByEmailContaining(String term);
}

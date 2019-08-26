package com.myroad2pro.beepos.service;

import java.util.List;

import com.myroad2pro.beepos.domain.Account;

public interface AccountService {
	Account findOne(String email);
	
	List<Account> search(String term);
}

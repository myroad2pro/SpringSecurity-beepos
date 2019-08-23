package com.myroad2pro.beepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account findOne(String email) {
		return accountRepository.findByEmail(email);
	}

}

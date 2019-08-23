package com.myroad2pro.beepos.service;

import com.myroad2pro.beepos.domain.Account;

public interface AccountService {
	Account findOne(String email);
}

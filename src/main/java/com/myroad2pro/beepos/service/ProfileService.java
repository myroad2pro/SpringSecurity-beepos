package com.myroad2pro.beepos.service;

import com.myroad2pro.beepos.domain.Profile;

public interface ProfileService {
	Profile findOne(Integer accountId);
}

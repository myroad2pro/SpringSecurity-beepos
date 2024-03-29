package com.myroad2pro.beepos.service;

import java.util.List;

import com.myroad2pro.beepos.domain.Profile;

public interface ProfileService {
	Profile findOne(Integer accountId);
	
	List<Profile> search(String term);
	
	void updateProfileIntroduction(Integer profileId, String introduction);
	
	void updateProfileHobby(Integer profileId, String hobby);
}

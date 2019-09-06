package com.myroad2pro.beepos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroad2pro.beepos.domain.Profile;
import com.myroad2pro.beepos.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile findOne(Integer accountId) {
		return profileRepository.findByAccountId(accountId);
	}

	@Override
	public List<Profile> search(String term) {
		return profileRepository.findByNameContaining(term);
	}

	@Override
	@Transactional
	public void updateProfileIntroduction(Integer profileId, String introduction) {
		// TODO Auto-generated method stub
		profileRepository.updateProfileIntroduction(profileId, introduction);
	}

	@Override
	@Transactional
	public void updateProfileHobby(Integer profileId, String hobby) {
		// TODO Auto-generated method stub
		profileRepository.updateProfileHobby(profileId, hobby);
	}

}

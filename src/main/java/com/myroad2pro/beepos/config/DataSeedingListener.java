package com.myroad2pro.beepos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.myroad2pro.beepos.domain.Role;
import com.myroad2pro.beepos.repository.AccountRepository;
import com.myroad2pro.beepos.repository.BranchRepository;
import com.myroad2pro.beepos.repository.ClapRepository;
import com.myroad2pro.beepos.repository.DepartmentRepository;
import com.myroad2pro.beepos.repository.GiftRepository;
import com.myroad2pro.beepos.repository.HashtagRepository;
import com.myroad2pro.beepos.repository.ProfileRepository;
import com.myroad2pro.beepos.repository.RoleRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ClapRepository clapRepository;
	
	@Autowired
	private GiftRepository giftRepository;
	
	@Autowired
	private HashtagRepository hashtagRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Role
		if(roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		if(roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		// Admin
		if(accountRepository.findByEmail("admin@vbee.com") == null) {
			
		}
	}
	
	
}

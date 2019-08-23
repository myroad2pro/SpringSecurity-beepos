package com.myroad2pro.beepos.config;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.domain.Branch;
import com.myroad2pro.beepos.domain.Department;
import com.myroad2pro.beepos.domain.Gender;
import com.myroad2pro.beepos.domain.Profile;
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
	private PasswordEncoder passwordEncoder;
	
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
		// Main Branch
		if(branchRepository.findByName("Main Branch") == null) {
			branchRepository.save(new Branch("Main Branch", "Hanoi, Vietnam"));
		}
		
		// Departments
		if(departmentRepository.findByNameContaining("Accounting") == null) {
			departmentRepository.save(new Department("Accounting", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Human Resources") == null) {
			departmentRepository.save(new Department("Human Resources", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Production") == null) {
			departmentRepository.save(new Department("Production", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Purchasing") == null) {
			departmentRepository.save(new Department("Purchasing", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Sales") == null) {
			departmentRepository.save(new Department("Sales", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Marketing") == null) {
			departmentRepository.save(new Department("Marketing", branchRepository.findByName("Main Branch")));
		}
		
		if(departmentRepository.findByNameContaining("Administration") == null) {
			departmentRepository.save(new Department("Administration", branchRepository.findByName("Main Branch")));
		}
		
		// Role
		if(roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		if(roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		// Admin Profile and Account
		if(accountRepository.findByEmail("admin@bee.com") == null) {
			Account admin = new Account();
			admin.setEmail("admin@bee.com");
			admin.setPassword(passwordEncoder.encode("admin@bee20190820"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			accountRepository.save(admin);
		}
		
		if(profileRepository.findByNameContaining("Admin").isEmpty()) {
			Profile adminProfile = new Profile();
			adminProfile.setName("Admin");
			adminProfile.setAccount(accountRepository.findByEmail("admin@bee.com"));
			adminProfile.setBirthday(LocalDate.now());
			adminProfile.setGender(Gender.MALE);
			adminProfile.setDepartment(departmentRepository.findByName("Administration"));
			profileRepository.save(adminProfile);
		}
		
		// Member
		if(accountRepository.findByEmail("member@bee.com") == null) {
			Account member = new Account();
			member.setEmail("member@bee.com");
			member.setPassword(passwordEncoder.encode("member@bee20190820"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			member.setRoles(roles);
			accountRepository.save(member);
		}
		
		if(profileRepository.findByNameContaining("Member").isEmpty()) {
			Profile memberProfile = new Profile();
			memberProfile.setName("Member");
			memberProfile.setAccount(accountRepository.findByEmail("member@bee.com"));
			memberProfile.setBirthday(LocalDate.now());
			memberProfile.setGender(Gender.MALE);
			memberProfile.setDepartment(departmentRepository.findByName("Sales"));
			profileRepository.save(memberProfile);
		}
	}
	
	
}

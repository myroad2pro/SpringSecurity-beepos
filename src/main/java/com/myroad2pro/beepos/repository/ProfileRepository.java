package com.myroad2pro.beepos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Gender;
import com.myroad2pro.beepos.domain.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
	Profile findByName(String name);
	
	List<Profile> findByNameContaining(String term);
	
	List<Profile> findByGender(Gender gender);
	
	List<Profile> findByBirthdayBetween(Date startDate, Date endDate);
	
	List<Profile> findByDepartmentId(Integer departmentId);
	
	List<Profile> findByAccountId(Integer accountId);
}

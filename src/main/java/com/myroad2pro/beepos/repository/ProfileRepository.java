package com.myroad2pro.beepos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Gender;
import com.myroad2pro.beepos.domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	Profile findByName(String name);
	
	List<Profile> findByNameContaining(String term);
	
	List<Profile> findByGender(Gender gender);
	
	List<Profile> findByBirthdayBetween(Date startDate, Date endDate);
	
	List<Profile> findByDepartmentId(Integer departmentId);
	
	Profile findByAccountId(Integer accountId);
	
	@Modifying
	@Query("UPDATE Profile p SET p.introduction = :introduction WHERE p.id = :profileId")
	int updateProfileIntroduction(@Param("profileId") Integer profileId, @Param("introduction") String introduction);
	
	@Modifying
	@Query("UPDATE Profile p SET p.hobby = :hobby WHERE p.id = :profileId")
	int updateProfileHobby(@Param("profileId") Integer profileId, @Param("hobby") String hobby);
}

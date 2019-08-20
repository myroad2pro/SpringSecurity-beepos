package com.myroad2pro.beepos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Gift;

@Repository
public interface GiftRepository extends CrudRepository<Gift, Integer>{
	List<Gift> findByMessageContaining(String term);
	
	List<Gift> findByPointGreaterThan(Integer point);
	
	List<Gift> findByPointLessThan(Integer point);
	
	List<Gift> findByPointBetween(Integer startPoint, Integer endPoint);
	
	List<Gift> findBySentTimeBefore(Date date);
	
	List<Gift> findBySentTimeAfter(Date date);
	
	List<Gift> findBySentTimeBetween(Date startDate, Date endDate);
	
	List<Gift> findBySenderId(Integer senderId);
	
	List<Gift> findByReceiverId(Integer receiverId);
	
	List<Gift> findByHashtagId(Integer hashtagId);
}

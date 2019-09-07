package com.myroad2pro.beepos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer>{
	List<Gift> findByMessageContaining(String term);
	
	List<Gift> findByPointGreaterThan(Integer point);
	
	List<Gift> findByPointLessThan(Integer point);
	
	List<Gift> findByPointBetween(Integer startPoint, Integer endPoint);
	
	List<Gift> findBySentTimeBefore(Date date);
	
	List<Gift> findBySentTimeAfter(Date date);
	
	List<Gift> findBySentTimeBetween(Date startDate, Date endDate);
	
	List<Gift> findBySenderId(Integer senderId);
	
	List<Gift> findByReceiverId(Integer receiverId);
	
	List<Gift> findBySenderIdOrReceiverId(Integer senderId, Integer receiverId);
	
	List<Gift> findByHashtagId(Integer hashtagId);
	
	List<Gift> findByMessageContainingOrderBySentTimeDesc(String term);
	
	List<Gift> findByPointGreaterThanOrderBySentTimeDesc(Integer point);
	
	List<Gift> findByPointLessThanOrderBySentTimeDesc(Integer point);
	
	List<Gift> findByPointBetweenOrderBySentTimeDesc(Integer startPoint, Integer endPoint);
	
	List<Gift> findBySentTimeBeforeOrderBySentTimeDesc(Date date);
	
	List<Gift> findBySentTimeAfterOrderBySentTimeDesc(Date date);
	
	List<Gift> findBySentTimeBetweenOrderBySentTimeDesc(Date startDate, Date endDate);
	
	List<Gift> findBySenderIdOrderBySentTimeDesc(Integer senderId);
	
	List<Gift> findByReceiverIdOrderBySentTimeDesc(Integer receiverId);
	
	List<Gift> findBySenderIdOrReceiverIdOrderBySentTimeDesc(Integer senderId, Integer receiverId);
	
	List<Gift> findByHashtagIdOrderBySentTimeDesc(Integer hashtagId);
	
	List<Gift> findAllByOrderBySentTimeDesc();
	
	Page<Gift> findAllByOrderBySentTimeDesc(Pageable pageable);
}

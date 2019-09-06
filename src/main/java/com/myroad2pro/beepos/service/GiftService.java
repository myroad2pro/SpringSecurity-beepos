package com.myroad2pro.beepos.service;

import java.util.List;

import com.myroad2pro.beepos.domain.Gift;

public interface GiftService {
	Iterable<Gift> findAll();
	
	List<Gift> findSentGifts(Integer senderId);
	
	List<Gift> findReceivedGifts(Integer receiverId);
	
	List<Gift> findUserGifts(Integer userId);
	
	Integer totalSentPoints(Integer senderId);
	
	Integer totalReceivedPoints(Integer receiverId);

	public Gift createAndSave(Integer senderId, Integer receiverId, String hashTag, Integer points, String message);
}

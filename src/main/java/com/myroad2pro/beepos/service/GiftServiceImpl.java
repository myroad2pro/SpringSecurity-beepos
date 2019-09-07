package com.myroad2pro.beepos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.domain.Gift;
import com.myroad2pro.beepos.domain.Hashtag;
import com.myroad2pro.beepos.repository.AccountRepository;
import com.myroad2pro.beepos.repository.GiftRepository;
import com.myroad2pro.beepos.repository.HashtagRepository;

@Service
public class GiftServiceImpl implements GiftService{

	@Autowired
	private GiftRepository giftRepository;
	
	@Autowired
	private HashtagRepository hashtagRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Iterable<Gift> findAll() {
		return giftRepository.findAll(); 
	}

	@Override
	public Gift createAndSave(Integer senderId, Integer receiverId, String hashTag, Integer points, String message) {
		Account sender = accountRepository.findById(senderId).get();
		Account receiver = accountRepository.findById(receiverId).get();
		if(sender != null && receiver != null) {
			Hashtag hashtag = hashtagRepository.findByTag(hashTag);
			Date sentTime = new Date();
			return giftRepository.save(new Gift(message, points, sentTime, sender, receiver, hashtag));
		}else return null;
	}

	@Override
	public List<Gift> findSentGifts(Integer senderId) {
		return giftRepository.findBySenderIdOrderBySentTimeDesc(senderId);
	}

	@Override
	public List<Gift> findReceivedGifts(Integer receiverId) {
		// TODO Auto-generated method stub
		return giftRepository.findByReceiverIdOrderBySentTimeDesc(receiverId);
	}

	
	@Override
	public Integer totalSentPoints(Integer senderId) {
		// TODO Auto-generated method stub
		List<Gift> sentGifts = findSentGifts(senderId);
		Integer totalSentPoints = 0;
		for(Gift gift : sentGifts) {
			totalSentPoints += gift.getPoint();
		}
		return totalSentPoints;
	}

	@Override
	public Integer totalReceivedPoints(Integer receiverId) {
		// TODO Auto-generated method stub
		List<Gift> receivedGifts = findReceivedGifts(receiverId);
		Integer totalReceivedPoints = 0;
		for(Gift gift : receivedGifts) {
			totalReceivedPoints += gift.getPoint();
		}
		return totalReceivedPoints;
	}

	@Override
	public List<Gift> findUserGifts(Integer userId) {
		// TODO Auto-generated method stub
		List<Gift> giftList = giftRepository.findBySenderIdOrReceiverIdOrderBySentTimeDesc(userId, userId);
		return giftList;
	}

	@Override
	public List<Gift> findAllOrderBySenttimeDesc() {
		// TODO Auto-generated method stub
		return giftRepository.findAllByOrderBySentTimeDesc();
	}
	
	
}

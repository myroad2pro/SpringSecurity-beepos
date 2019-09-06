package com.myroad2pro.beepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroad2pro.beepos.domain.Hashtag;
import com.myroad2pro.beepos.repository.HashtagRepository;

@Service
public class HashtagServiceImpl implements HashtagService {

	@Autowired
	private HashtagRepository hashtagRepository;
	
	@Override
	public Iterable<Hashtag> findAll() {
		return hashtagRepository.findAll();
	}

}

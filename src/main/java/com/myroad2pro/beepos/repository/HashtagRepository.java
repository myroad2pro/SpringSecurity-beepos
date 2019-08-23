package com.myroad2pro.beepos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Hashtag;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Integer>{
	Hashtag findByTag(String tag);
}

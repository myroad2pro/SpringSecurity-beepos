package com.myroad2pro.beepos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myroad2pro.beepos.domain.Clap;

@Repository
public interface ClapRepository extends CrudRepository<Clap, Integer>{
	List<Clap> findByGiftId(Integer giftId);
	
	List<Clap> findByAccountId(Integer accountId);
}

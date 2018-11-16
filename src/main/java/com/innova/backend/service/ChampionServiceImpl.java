package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.ChampionDao;
import com.innova.backend.model.Champion;

@Service
@Transactional(readOnly = true)
public class ChampionServiceImpl implements ChampionService {

	   @Autowired
	   private ChampionDao championDao;

	   @Transactional
	   @Override
	   public long save(Champion champion) {
	      return championDao.save(champion);
	   }

	   @Override
	   public Champion get(long id) {
	      return championDao.get(id);
	   }

	   @Override
	   public List<Champion> list() {
	      return championDao.list();
	   }

	   @Transactional
	   @Override
	   public void update(long id, Champion champion) {
	      championDao.update(id, champion);
	   }

	   @Transactional
	   @Override
	   public void delete(long id) {
	      championDao.delete(id);
	   }

}

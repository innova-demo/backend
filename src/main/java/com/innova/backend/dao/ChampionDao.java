package com.innova.backend.dao;

import java.util.List;

import com.innova.backend.model.Champion;

public interface ChampionDao {

	   long save(Champion champion);
	   Champion get(long id);
	   List<Champion> list();
	   void update(long id, Champion champion);
	   void delete(long id);
	   
	}


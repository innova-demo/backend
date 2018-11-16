package com.innova.backend.service;

import java.util.List;

import com.innova.backend.model.Champion;

public interface ChampionService {

	   long save(Champion champion);
	   Champion get(long id);
	   List<Champion> list();
	   void update(long id, Champion champion);
	   void delete(long id);

}

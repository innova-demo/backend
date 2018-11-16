package com.innova.backend.dao;

import java.util.List;

import com.innova.backend.model.Champion;
import com.innova.backend.model.Team;

public interface TeamDao {

   long save(Team equipo);
   Team get(long id);
   List<Team> list();
   List<Champion> championList(long id);   
   void update(long id, Team equipo);
   void delete(long id);
   
}


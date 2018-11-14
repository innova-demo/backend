package com.innova.backend.dao;

import java.util.List;

import com.innova.backend.model.Team;

public interface EquipoDao {

   long save(Team equipo);
   Team get(long id);
   List<Team> list();
   void update(long id, Team equipo);
   void delete(long id);
   
}


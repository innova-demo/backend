package com.innova.backend.service;

import java.util.List;

import com.innova.backend.model.Team;

public interface EquipoService {

   long save(Team equipo);
   Team get(long id);
   List<Team> list();
   void update(long id, Team equipo);
   void delete(long id);
}

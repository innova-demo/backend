package com.innova.backend.service;

import java.util.List;

import com.innova.backend.model.Team;

public interface TeamService {

   long save(Team team);
   Team get(long id);
   List<Team> list();
   void update(long id, Team team);
   void delete(long id);
}

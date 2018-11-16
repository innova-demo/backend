package com.innova.backend.service;

import java.util.List;

import com.innova.backend.model.Champion;
import com.innova.backend.model.Team;

public interface TeamService {

   long save(Team team);
   long saveChampion(long id, Champion champion);
   Team get(long id);
   List<Team> list();
   List<Champion> championList(long id);   
   void update(long id, Team team);
   void delete(long id);
}

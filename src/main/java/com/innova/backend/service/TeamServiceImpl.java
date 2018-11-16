package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.ChampionDao;
import com.innova.backend.dao.TeamDao;
import com.innova.backend.model.Champion;
import com.innova.backend.model.Team;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

   @Autowired
   private TeamDao teamDao;

   @Autowired
   private ChampionDao championDao;

   @Transactional
   @Override
   public long save(Team team) {
      return teamDao.save(team);
   }

   @Transactional
   @Override
   public long saveChampion(long id, Champion champion) {
	   Team team = this.get(id);
	   champion.setTeam(team);
	   return championDao.save(champion);
   }

   @Override
   public Team get(long id) {
      return teamDao.get(id);
   }

   @Override
   public List<Team> list() {
      return teamDao.list();
   }
   
   @Override
   public List<Champion> championList(long id) {
      return teamDao.championList(id);
   }

   @Transactional
   @Override
   public void update(long id, Team team) {
      teamDao.update(id, team);
   }

   @Transactional
   @Override
   public void delete(long id) {
      teamDao.delete(id);
   }

}

package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.TeamDao;
import com.innova.backend.model.Team;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

   @Autowired
   private TeamDao teamDao;

   @Transactional
   @Override
   public long save(Team team) {
      return teamDao.save(team);
   }

   @Override
   public Team get(long id) {
      return teamDao.get(id);
   }

   @Override
   public List<Team> list() {
      return teamDao.list();
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

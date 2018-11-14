package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.EquipoDao;
import com.innova.backend.model.Team;

@Service
@Transactional(readOnly = true)
public class EquipoServiceImpl implements EquipoService {

   @Autowired
   private EquipoDao equipoDao;

   @Transactional
   @Override
   public long save(Team equipo) {
      return equipoDao.save(equipo);
   }

   @Override
   public Team get(long id) {
      return equipoDao.get(id);
   }

   @Override
   public List<Team> list() {
      return equipoDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Team equipo) {
      equipoDao.update(id, equipo);
   }

   @Transactional
   @Override
   public void delete(long id) {
      equipoDao.delete(id);
   }

}

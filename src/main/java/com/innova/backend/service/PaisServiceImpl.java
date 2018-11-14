package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.PaisDao;
import com.innova.backend.model.Country;

@Service
@Transactional(readOnly = true)
public class PaisServiceImpl implements PaisService {

   @Autowired
   private PaisDao paisDao;

   @Transactional
   @Override
   public long save(Country pais) {
      return paisDao.save(pais);
   }

   @Override
   public Country get(long id) {
      return paisDao.get(id);
   }

   @Override
   public List<Country> list() {
      return paisDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Country pais) {
      paisDao.update(id, pais);
   }

   @Transactional
   @Override
   public void delete(long id) {
      paisDao.delete(id);
   }

}

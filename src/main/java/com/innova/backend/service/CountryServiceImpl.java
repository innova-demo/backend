package com.innova.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.backend.dao.CountryDao;
import com.innova.backend.model.Country;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

   @Autowired
   private CountryDao countryDao;

   @Transactional
   @Override
   public long save(Country country) {
      return countryDao.save(country);
   }

   @Override
   public Country get(long id) {
      return countryDao.get(id);
   }

   @Override
   public List<Country> list() {
      return countryDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Country country) {
      countryDao.update(id, country);
   }

   @Transactional
   @Override
   public void delete(long id) {
      countryDao.delete(id);
   }

}

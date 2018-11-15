package com.innova.backend.dao;

import java.util.List;

import com.innova.backend.model.Country;

public interface CountryDao {

   long save(Country country);
   Country get(long id);
   List<Country> list();
   void update(long id, Country country);
   void delete(long id);
   
}


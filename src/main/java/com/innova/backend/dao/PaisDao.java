package com.innova.backend.dao;

import java.util.List;

import com.innova.backend.model.Country;

public interface PaisDao {

   long save(Country equipo);
   Country get(long id);
   List<Country> list();
   void update(long id, Country equipo);
   void delete(long id);
   
}


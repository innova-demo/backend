package com.innova.backend.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innova.backend.model.Country;

@Repository
public class PaisDaoImpl implements PaisDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public long save(Country pais) {
      sessionFactory.getCurrentSession().save(pais);
      return pais.getId();
   }

   @Override
   public Country get(long id) {
      return sessionFactory.getCurrentSession().get(Country.class, id);
   }

   @Override
   public List<Country> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Country> cq = cb.createQuery(Country.class);
      Root<Country> root = cq.from(Country.class);
      cq.select(root);
      Query<Country> query = session.createQuery(cq);
      return query.getResultList();
   }

   @Override
   public void update(long id, Country equipo) {
      Session session = sessionFactory.getCurrentSession();
      Country equipo2 = session.byId(Country.class).load(id);
      equipo2.setName(equipo.getName());
      session.flush();
   }

   @Override
   public void delete(long id) {
      Session session = sessionFactory.getCurrentSession();
      Country equipo = session.byId(Country.class).load(id);
      session.delete(equipo);
   }

}
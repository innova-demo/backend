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

import com.innova.backend.model.Champion;

@Repository
public class ChampionDaoImpl implements ChampionDao {
	   @Autowired
	   private SessionFactory sessionFactory;

	   @Override
	   public long save(Champion champion) {
	      sessionFactory.getCurrentSession().saveOrUpdate(champion);
	      return champion.getId();
	   }

	   @Override
	   public Champion get(long id) {
	      return sessionFactory.getCurrentSession().get(Champion.class, id);
	   }

	   @Override
	   public List<Champion> list() {
	      Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Champion> cq = cb.createQuery(Champion.class);
	      Root<Champion> root = cq.from(Champion.class);
	      cq.select(root);
	      Query<Champion> query = session.createQuery(cq);
	      return query.getResultList();
	   }

	   @Override
	   public void update(long id, Champion equipo) {
	      sessionFactory.getCurrentSession().saveOrUpdate(equipo);
	   }

	   @Override
	   public void delete(long id) {
	      Session session = sessionFactory.getCurrentSession();
	      Champion equipo = session.byId(Champion.class).load(id);
	      session.delete(equipo);
	   }

}

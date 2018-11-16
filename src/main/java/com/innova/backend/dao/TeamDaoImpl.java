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

import com.innova.backend.exception.CustomApiException;
import com.innova.backend.model.Champion;
import com.innova.backend.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public long save(Team equipo) {
      sessionFactory.getCurrentSession().saveOrUpdate(equipo);
      return equipo.getId();
   }

   @Override
   public Team get(long id) {
      return sessionFactory.getCurrentSession().get(Team.class, id);
   }

   @Override
   public List<Team> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Team> cq = cb.createQuery(Team.class);
      Root<Team> root = cq.from(Team.class);
      cq.select(root);
      Query<Team> query = session.createQuery(cq);
      return query.getResultList();
   }

   @Override
   public List<Champion> championList(long id) {
	   Session session = sessionFactory.getCurrentSession();
	   Team team = session.byId(Team.class).load(id);
	   return team.getChampions();      
   }

   @Override
   public void update(long id, Team equipo) {
      sessionFactory.getCurrentSession().saveOrUpdate(equipo);
   }

   @Override
   public void delete(long id) {
      Session session = sessionFactory.getCurrentSession();
      Team team = session.byId(Team.class).load(id);
      session.delete(team);
   }

}
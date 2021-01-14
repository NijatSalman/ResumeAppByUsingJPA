package com.company.impl;

import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.UserSkill;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {
	
	@Override
	public List<UserSkill> getAllSkillByUserId(int userId) {
		EntityManager em=em();
                Query query=em.createQuery("Select us from UserSkill us", UserSkill.class);
                List<UserSkill> list=query.getResultList();
                em.close();
                return list;
	}

	@Override
	public boolean insertUserSkill(UserSkill obj) {
		EntityManager em=em();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
                em.close();
                return true;
	}

	@Override
	public boolean updateUserSkill(UserSkill obj) {
                EntityManager em=em();
                em.getTransaction().begin();
                em.merge(obj);
                em.getTransaction().commit();
                em.close();
                return true;
	}

	@Override
	public boolean removeUserSkill(int id) {
		EntityManager em=em();
                em.getTransaction().begin();
                UserSkill us=em.find(UserSkill.class, id);
                em.remove(us);
                em.getTransaction().commit();
                em.close();
                return true;
	}

}

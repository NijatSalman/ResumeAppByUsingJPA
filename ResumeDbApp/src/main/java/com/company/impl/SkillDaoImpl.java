package com.company.impl;

import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{

	
	@Override
	public List<Skill> getAllSkill() {
		          EntityManager em=em();
                          Query query=em.createQuery("Select s from Skill s", Skill.class);
                          List<Skill> list=query.getResultList();
                          em.close();
                          return list;
	}

	@Override
	public Skill getSkillById(int id) {
		EntityManager em=em();
                Skill skill=em.find(Skill.class, id);
                em.close();
                return skill;
	}

	@Override
	public boolean updateSkill(Skill obj) {
		EntityManager em=em();
                em.getTransaction().begin();
                em.merge(obj);
                em.getTransaction().commit();
                em.close();
                return true;
	}

	@Override
	public boolean addSkill(Skill obj) {
		EntityManager em=em();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
                em.close();
                return true;
	}

	
	
}

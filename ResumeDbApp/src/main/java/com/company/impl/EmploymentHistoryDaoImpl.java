package com.company.impl;

import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

	@Override
	public List<EmploymentHistory> getAllEmploymentHistoryById(int id) {
		EntityManager em=em();
                Query query=em.createQuery("Select emp from EmploymentHistory emp", EmploymentHistory.class);
                List<EmploymentHistory> list=query.getResultList();
                return list;
	}

	@Override
	public boolean addEmploymentHistory(EmploymentHistory obj) {
		          EntityManager em=em();
                          em.getTransaction().begin();
                          em.persist(obj);
                          em.getTransaction().commit();
                          em.close();
                          return true;
	}

	@Override
	public boolean updateEmploymentHistory(EmploymentHistory obj) {
		EntityManager em=em();
                em.getTransaction().begin();
                em.merge(obj);
                em.getTransaction().commit();
                em.close();
                return true;
	}

	@Override
	public boolean removeEmploymentHistory(int id) {
		EntityManager em=em();
                          em.getTransaction().begin();
                          List<EmploymentHistory> emp=getAllEmploymentHistoryById(id);                
                          em.remove(emp);
                          em.getTransaction().commit();
                          em.close();
                          return true;
	}
	
}

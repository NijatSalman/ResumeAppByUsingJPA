package com.company.impl;

import java.util.List;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
	
	@Override
	public List<Country> getAllCountry() {
		          EntityManager em=em();
                          Query query=em.createQuery("Select c from Country c", Country.class);
			List<Country> list=query.getResultList();
                        return list;
	}

	@Override
	public boolean addCountry(Country obj) {
         EntityManager em=em();
         em.getTransaction().begin();
         em.persist(obj);
         em.getTransaction().commit();
         
         em.close();
         return true;
	}

	@Override
	public boolean updateCountry(Country obj) {
		   EntityManager em=em();
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            em.close();
            return true;
	}

	@Override
	public Country getById(int id) {
		EntityManager em=em();
                Country country=em.find(Country.class, id);
                em.close();
                return country;
	}

}

package com.company.impl;

import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {


	@Override
	public List<User> getAll() {
		EntityManager em=em();
               
                Query query=em.createQuery("Select u from User u", User.class);
                List<User> list=query.getResultList();	
		
                return list;	
	}

//  method typed by Nicat Salmanov with JPQL 
//	@Override
//	public User findByEmailAndPassword(String email, String password) {
//		EntityManager em=em();
//                Query query=em.createQuery("Select u from User u where u.email=:e and u.password=:p", User.class);
//                query.setParameter("e", email);
//                query.setParameter("p", password);
//                
//                List<User> list=query.getResultList();
//                
//                if(list.size()==1){
//                    return list.get(0);
//                }
//                return null;
//	}

        // method typed by Nicat Salmanov with CriteriaBuilder
        @Override
	public User findByEmailAndPassword(String email, String password) {
		EntityManager em=em();
                    CriteriaBuilder cb=em.getCriteriaBuilder();
                    CriteriaQuery<User> q1=cb.createQuery(User.class);
                    Root<User> postRoot=q1.from(User.class);
                    CriteriaQuery<User> q2=q1
                            .where(cb.equal(postRoot.get("email"),email),cb.equal(postRoot.get("password"),password));
                
                    Query query=em.createQuery(q2);
                    
                List<User> list=query.getResultList();
                
                if(list.size()==1){
                    return list.get(0);
                }
                return null;
	}
   
        // method typed by Nicat Salmanov with JPQL
//	@Override
//	public User findByEmail(String email) {
//		EntityManager em=em();
//                Query query=em.createQuery("Select u from User u where u.email=:e", User.class);
//                query.setParameter("e", email);
//                
//                List<User> list=query.getResultList();
//                
//                if(list.size()==1){
//                    return list.get(0);
//                }
//                return null;
//	}
       
        // method typed by Nicat Salmanov with CriteriaBuilder
        @Override
	public User findByEmail(String email) {
		EntityManager em=em();
                    CriteriaBuilder cb=em.getCriteriaBuilder();
                    CriteriaQuery<User> q1=cb.createQuery(User.class);
                    Root<User> postRoot=q1.from(User.class);
                    CriteriaQuery<User> q2=q1
                            .where(cb.equal(postRoot.get("email"),email));
                
                    Query query=em.createQuery(q2);
                    
                List<User> list=query.getResultList();
                
                if(list.size()==1){
                    return list.get(0);
                }
                return null;
	}
        
        
// method typed by Nicat Salmanov with NamedQuery
//        @Override
//	public User findByEmail(String email) {
//		EntityManager em=em();
//   
//                    Query query=em.createNamedQuery("User.findByEmail",User.class);
//                    query.setParameter("email", email);
//                    
//                List<User> list=query.getResultList();
//                
//                if(list.size()==1){
//                    return list.get(0);
//                }
//                return null;
//	}
        
        
        // method typed by Nicat Salmanov with NativeQeury which is normal Sql query in java
//        @Override
//	public User findByEmail(String email) {
//		EntityManager em=em();
//   
//                    Query query=em.createNativeQuery("User.findByEmail",User.class);
//                    query.setParameter(1, email);
//                    
//                List<User> list=query.getResultList();
//                
//                if(list.size()==1){
//                    return list.get(0);
//                }
//                return null;
//	}
        
	@Override
	public List<User> getAll(String name,String surname,Integer nationalityId) {
		EntityManager em=em();
                
                String jpql="select u from User u where 1=1";

			if (name!=null && !name.trim().isEmpty()){
				jpql+=" and u.name=:name";
			}
			if(surname!=null && !surname.trim().isEmpty()){
				jpql+=" and u.surname=:surname";
			}
			if(nationalityId!=null){
				jpql+=" and u.nationality.id=:nid";
			}
                        
                        Query query=em.createQuery(jpql, User.class);
                        
			if (name!=null && !name.trim().isEmpty()){
				query.setParameter("name", name);
			}
			if (surname!=null && !surname.trim().isEmpty()){
				query.setParameter("surname", surname);
			}
			if (nationalityId!=null){
				query.setParameter("nid", nationalityId);
			}
			
		return query.getResultList();
	}

	@Override
	public boolean updateUser(User u) {		
                EntityManager em =em();
             
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
            
            em.close();
            return true;		
	}


	@Override
	public boolean removeUser(int id) {
		
                EntityManager em = em();
             
                em.getTransaction().begin();
            User user=em.find(User.class,id);
            em.remove(user);
            em.getTransaction().commit();
            
            em.close();
           
            return true;		
	}

	@Override
	public User getById(int userId) {
		  EntityManager em = em( );
                
            User user=em.find(User.class, userId);
            
            em.close();
           
                return user;
	}

	private BCrypt.Hasher crypt=BCrypt.withDefaults();
	@Override
	public boolean addUser(User u) {
            EntityManager em = em();
            u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            
            em.close();
            return true;
		
	}
}

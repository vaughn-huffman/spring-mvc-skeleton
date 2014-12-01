package com.xpanxion.skeleton.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.xpanxion.skeleton.dto.entity.UserEntity;

/**
 * Implementation of the UserDao interface. 
 * 
 * @author vhuffman
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
    /**
     * Returns all of the users.
     * 
     * @return all of the users. 
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() {
		List<UserEntity> entities = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entities = session.createQuery("FROM UserEntity").list();
			tx.commit();
		}catch (HibernateException he) {
			if (tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}finally {
			session.close();
		
		}
		if (entities !=null) {
			return entities;
		}
		else {
			return new ArrayList<UserEntity>();
		}
		 
	}
	
    /**
     * Returns the user given user's id
     * 
     * @return  the user. 
     */
	@Override
	public UserEntity getUser(long id) {
		UserEntity entity = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = (UserEntity)session.get(UserEntity.class, id);
			tx.commit();
		}catch (HibernateException he) {
			if (tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}finally {
			session.close();
		
		}
		if (entity !=null) {
			return entity;
		}
		else {
			return new UserEntity();
		}
	}
	
    /**
     * Returns Creates/Adds the user
     * 
     * @return  the user. 
     */
	@Override
	public UserEntity addUser(UserEntity user) {
		UserEntity entity = user;
		long userId = -1;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userId = (long) session.save(user);
			tx.commit();
		}catch (HibernateException he) {
			if (tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}finally {
			session.close();
		
		}
		
		//Get user that was just added to populate user.Id
		if (userId != -1)
		{
			entity = getUser(userId);
		}
		
		System.out.println("userid " + entity.getId());
		System.out.println("username " + entity.getUsername());
		System.out.println("user pw " + entity.getPassword());
		
		return entity;
	}
	
    /**
     * Returns Updates password for user
     * 
     * @return  the user. 
     */
	@Override
	public UserEntity updateUser(long id, UserEntity user) {
		UserEntity entity = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = (UserEntity) session.get(UserEntity.class, id);
			entity.setPassword(user.getPassword());
			tx.commit();
		}catch (HibernateException he) {
			if (tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}finally {
			session.close();
		
		}
		if (entity !=null) {
			return entity;
		}
		else {
			return new UserEntity();
		}
	}
	
    /**
     * Returns the user given user's id
     * 
     * @return  the user. 
     */
	@Override
	public UserEntity deleteUser(long id) {
		UserEntity entity = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			entity = (UserEntity)session.get(UserEntity.class, id);
			session.delete(entity);
			tx.commit();
		}catch (HibernateException he) {
			if (tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}finally {
			session.close();
		
		}
		if (entity !=null) {
			return entity;
		}
		else {
			return new UserEntity();
		}
	}
	
	
	/**
     * Sets the session factory for this dao to use. 
     * 
     * @param factory the session factory for this dao.
     */
    @Resource
    public void setSesionFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

}
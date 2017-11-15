package com.DaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.*;
import com.model.*;
import java.util.List;

import javax.transaction.Transactional;

@Repository("categoryDaoImpl")
public class CategoryDaoImpl {
	@Autowired
	SessionFactory sessionFac;
	public void insertCategory(Category category) 
	{
	Session session= sessionFac.openSession();
	session.beginTransaction();
	session.persist(category);
	session.getTransaction().commit();
	}
	
	@Autowired
	public CategoryDaoImpl(SessionFactory sessionFactory) 
	{
		super();
		sessionFac=sessionFactory;	
	}
	@Transactional
	@SuppressWarnings("deprecation")
	public List<Category> retrieve() {
		Session session=sessionFac.openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Category> li=session.createQuery("from Category").list();
			session.getTransaction().commit();
			return li;
	}

}

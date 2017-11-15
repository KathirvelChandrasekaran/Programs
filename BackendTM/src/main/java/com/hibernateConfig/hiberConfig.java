package com.hibernateConfig;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.Dao.*;
import com.DaoImpl.*;
import com.model.*;

@Configuration
@EnableTransactionManagement
@ComponentScan("demo")
public class hiberConfig 
{
	
	@Autowired
	@Bean(name="datasource")
	public DataSource getH2()
	{
		System.out.println("Hibernate initiated.....");
		DriverManagerDataSource dt=new DriverManagerDataSource();
		dt.setDriverClassName("org.h2.Driver");
		dt.setUrl("jdbc:h2:tcp://localhost/~/tm");
		dt.setUsername("sa");
		dt.setPassword("");
		System.out.println("connection established...");
		return dt;
	}
	
	private Properties getHiberProps() 
	{
	Properties  p=new Properties();
	p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
	p.put("hibernate.show_sql","true");
	p.put("hibernate.hbm2ddl.auto","update");
	System.out.println("test");
	return p;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSession(DataSource datasource)
	{
		LocalSessionFactoryBuilder lsfb=new LocalSessionFactoryBuilder(datasource);
		lsfb.addProperties(getHiberProps());
		lsfb.addAnnotatedClass(User.class);
		lsfb.addAnnotatedClass(Category.class);
		return lsfb.buildSessionFactory();
	}
	 
	@Autowired
	@Bean(name="userDaoImpl")
	public UserDao getUserData(SessionFactory sessionFac)
	{
		return new UserDaoImpl(sessionFac);
	}
	
	@Autowired
	@Bean(name="catDaoImpl")
	public CategoryDaoImpl getCarData(SessionFactory sessionFac)
	{
		return new CategoryDaoImpl(sessionFac);
	}

	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransaction(SessionFactory sessionFactory)
	{
		HibernateTransactionManager tm=new HibernateTransactionManager(sessionFactory);
		return tm;
	}

}

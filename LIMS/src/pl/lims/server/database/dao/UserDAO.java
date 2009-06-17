package pl.lims.server.database.dao;


import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.repackaged.com.google.common.collect.Lists;

import pl.lims.client.common.model.User;
import pl.lims.server.database.PMF;


public class UserDAO
{
	private PersistenceManagerFactory pmf = PMF.get();
	
	/** Sprawdza czy nazwa uzytkownika jest wolna */
	public boolean userNameAvailable(String name)
	{
		boolean res = false;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		query.setFilter("name == nameParam");
		query.declareParameters("String nameParam");
		
		try
		{
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>)query.execute(name);
			res = list.isEmpty();
		}
		finally
		{
			query.closeAll();
			pm.close();
		}
		
		return res;
	}
	
	
	public User loadUser(String name, String password)
	{
		User result = null;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		query.setFilter("name == nameParam && password == passParam");
		query.declareParameters("String nameParam, String passParam");
		
		try
		{
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>)query.execute(name,password);
			if(!list.isEmpty())
				result = list.get(0);
		}
		finally
		{
			query.closeAll();
			pm.close();
		}
		return result;
	}
	
	public void saveUser(User user)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		
		try
		{
			pm.makePersistent(user);
		}
		finally
		{
			pm.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<User> listUsers()
	{
		List<User> res = Collections.EMPTY_LIST;
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		
		try
		{
			
			res = (List<User>)query.execute();
		}
		finally
		{
			query.closeAll();
			pm.close();
		}
		return res;
	}
}

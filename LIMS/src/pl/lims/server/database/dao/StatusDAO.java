package pl.lims.server.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import pl.lims.client.common.model.Status;
import pl.lims.server.database.PMF;

public class StatusDAO
{
	private PersistenceManagerFactory pmf = PMF.get();
	
	public List<String> listStatusNames()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery(Status.class);
		List<String> names = new ArrayList<String>();
		try
		{
			List<Status> statusList = (List<Status>) q.execute();
			for (Status s : statusList)
			{
				names.add(s.getName());
			}
		}
		catch (Exception ex)
		{
			names.add("cannot retrieve status names ");
			ex.printStackTrace();
		}
		finally
		{
			pm.close();
		}
		if(!names.contains("New")) // ale obejscie;]
		{
			addStatus("New"); // oby tu nic zle nie poszlo
			names.add(0, "New");
		}
		return names;
	}
	
	public String addStatus(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Status s = new Status(name);

		Query q = pm.newQuery(Status.class);
		q.setFilter("name == nameParam");
		q.declareParameters("String nameParam");
		
		try
		{
			List<Status> existing = (List<Status>)q.execute(name);
			if(existing.isEmpty())
			{
				pm.makePersistent(s);
				return "Status successfuly added";
			}
			else
			{
				return "Such status already exists";
			}
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
	}

	public String removeStatus(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Status s = new Status(name);

		Query q = pm.newQuery(Status.class);
		q.setFilter("name == nameParam");
		q.declareParameters("String nameParam");
		
		try
		{
			List<Status> existing = (List<Status>)q.execute(name);
			if(existing.isEmpty())
			{
				return "There is no such item oO";
			}
			else
			{
				Status st = existing.get(0);
				pm.deletePersistent(st);
				return "Status removed";
			}
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
	}
}

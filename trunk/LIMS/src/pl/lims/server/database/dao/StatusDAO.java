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
			names.add("cannot retrieve status names");
		}
		finally
		{
			pm.close();
		}
		return names;
	}
	
	public String addStatus(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Status s = new Status(name);

		try
		{
			pm.makePersistent(s);
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
		return "success"; //uh
	}
}

package pl.lims.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import pl.lims.client.services.SetupManager;
import pl.lims.model.Status;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SetupManagerImpl extends RemoteServiceServlet implements SetupManager
{

	/**
	 * 
	 */
	private static final long						serialVersionUID	= 1L;
	public static final PersistenceManagerFactory	pmf					= JDOHelper
																			.getPersistenceManagerFactory("transactions-optional");

	public String addStatus(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Status s = new Status();
		s.setName(name);
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
		return "success";
	}

	public List<String> getStatusNames()
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
		return names;
	}

}

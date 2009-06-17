package pl.lims.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import pl.lims.client.common.model.Incident;
import pl.lims.client.common.model.User;
import pl.lims.client.services.IncidentManager;
import pl.lims.server.database.PMF;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IncidentManagerImpl extends RemoteServiceServlet implements IncidentManager
{
	private final PersistenceManagerFactory pmf = PMF.get();
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public String saveIncident(String name, String description)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Incident incident = new Incident();
		incident.setName(name);
		incident.setDescription(description);
		try
		{
			pm.makePersistent(incident);
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
		return "added incident: " + name + ", " + description;
	}

	public String addIncident(Incident i)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			pm.makePersistent(i);
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
		return "added incident";
	}

	public List<Incident> getIncidents() {
		List<Incident> res = Collections.EMPTY_LIST;
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Incident> res2 = new ArrayList<Incident>();
		Query query = pm.newQuery(Incident.class);
		try
		{
			res = (List<Incident>)query.execute();
			
			res2.addAll(res); // to cos co zwraca persistent manager nie jest serializable
		}
		finally
		{
			query.closeAll();
			pm.close();
		}
		
		return res2;
	}
}

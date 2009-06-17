package pl.lims.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import pl.lims.client.common.model.Incident;
import pl.lims.client.services.IncidentManager;
import pl.lims.server.database.PMF;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IncidentManagerImpl extends RemoteServiceServlet implements IncidentManager
{
	private final PersistenceManagerFactory pmf = PMF.get();
	PersistenceManager pm = pmf.getPersistenceManager();
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public String saveIncident(String name, String description)
	{
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
		ArrayList<Incident> incidents = new ArrayList<Incident>();
		incidents.addAll(pm.getManagedObjects(Incident.class));
		return incidents;
	}
}

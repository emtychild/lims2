package pl.lims.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import pl.lims.client.services.IncidentManager;
import pl.lims.model.Incident;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IncidentManagerImpl extends RemoteServiceServlet implements IncidentManager
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public String saveIncident(String name, String description)
	{
		Incident incident = new Incident();
		incident.setName(name);
		incident.setDescription(description);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory();
		PersistenceManager pm = pmf.getPersistenceManager();
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

}

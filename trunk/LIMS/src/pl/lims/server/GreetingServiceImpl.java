package pl.lims.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import pl.lims.client.common.model.Incident;
import pl.lims.client.services.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService
{

	public String greetServer(String input)
	{
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		Incident incident = new Incident();
		incident.setName(input);
		incident.setDescription(input);

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			pm.makePersistent(incident);
		}
		catch (Throwable ex)
		{
			return "Can't store entity:" + ex.getMessage();
		}
		finally
		{
			pm.close();
		}
		return "added incident: " + input + ", " + input;

		// return "Hello, " + input + "!<br><br>I am running " + serverInfo
		// + ".<br><br>It looks like you are using:<br>" + userAgent;
	}
}

package pl.lims.server;

import java.util.List;

import pl.lims.client.services.SetupManager;
import pl.lims.server.database.dao.StatusDAO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SetupManagerImpl extends RemoteServiceServlet implements SetupManager
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6182763580412856447L;
	private StatusDAO	statusDAO	= new StatusDAO();

	public String addStatus(String name)
	{
		return statusDAO.addStatus(name); // jak nizej ;]
	}

	public List<String> getStatusNames()
	{
		return statusDAO.listStatusNames(); // narazie tylko takie przekierowanie potem trza bedzie jakis ewentualne
											// info o bledach jakos sensowniej zwracac
	}

}

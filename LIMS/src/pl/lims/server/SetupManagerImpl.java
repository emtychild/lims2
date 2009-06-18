package pl.lims.server;

import java.util.List;

import pl.lims.client.services.SetupManager;
import pl.lims.server.database.dao.CategoryDAO;
import pl.lims.server.database.dao.StatusDAO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SetupManagerImpl extends RemoteServiceServlet implements SetupManager
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6182763580412856447L;
	private StatusDAO			statusDAO			= new StatusDAO();
	private CategoryDAO			categoryDAO			= new CategoryDAO();

	public String addStatus(String name)
	{
		return statusDAO.addStatus(name); // jak nizej ;]
	}

	public List<String> getStatusNames()
	{
		return statusDAO.listStatusNames(); // narazie tylko takie przekierowanie potem trza bedzie jakis ewentualne
		// info o bledach jakos sensowniej zwracac
	}

	public String removeStatus(String name)
	{
		return statusDAO.removeStatus(name);
	}

	public String addCategory(String name)
	{
		return categoryDAO.addCategory(name);
	}

	public List<String> getCategories()
	{
		return categoryDAO.listCategories();
	}

	public String removeCategory(String name)
	{
		return categoryDAO.removeCategory(name);
	}

}

package pl.lims.client;

import pl.lims.client.common.model.KnownIncident;
import pl.lims.client.common.model.User;
import pl.lims.client.tabs.AboutTab;
import pl.lims.client.tabs.ConfigurationTab;
import pl.lims.client.tabs.IncidentsTab;
import pl.lims.client.tabs.KnowledgeDBTab;
import pl.lims.client.tabs.UsersTab;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class Tabs extends LayoutContainer
{
	private TabPanel	folder	= new TabPanel();
	private User	user;

	public Tabs()
	{
		folder.setWidth(600);
		folder.setHeight(600);

		add(folder);
	}

	
	
	public User getUser()
	{
		return user;
	}



	public void setUser(User user)
	{
		this.user = user;
	}



	@Override
	protected void onLoad()
	{
		if (user == null)
		{
			showUserLoginWindow();
		}
		else
			init(user);
	}

	private void showUserLoginWindow()
	{
		LoginRegisterWindow window = new LoginRegisterWindow(this);
		window.show();
	}

	public void init(User user)
	{
		this.user = user;
		
		switch(user.getUserType())
		{
			case ADMIN:
				showAdminTabs();
				break;
			case TECHNICIAN:
				showTechnicianTabs();
				break;
			case REGULAR_USER:
				showUserTabs();
				break;
		}
		showCommonTabs();
	}

	private void showCommonTabs()
	{
		TabItem aboutTab = new TabItem("About LIMS");
		aboutTab.add(new AboutTab());
		folder.add(aboutTab); 
	}

	private void showUserTabs()
	{
		// TODO Auto-generated method stub
		
	}

	private void showTechnicianTabs()
	{
		TabItem incidentsTab = new TabItem("Incidents");
		incidentsTab.add(new IncidentsTab(this));
		folder.add(incidentsTab);
		
		TabItem knowledgeDB = new TabItem("Knowledge DB");
		knowledgeDB.add(new KnowledgeDBTab());
		folder.add(knowledgeDB);
	}

	private void showAdminTabs()
	{
		TabItem configTab = new TabItem("Configuration");
		configTab.add(new ConfigurationTab());
		folder.add(configTab);
		
		TabItem usersTab = new TabItem("Users");
		usersTab.add(new UsersTab());
		folder.add(usersTab);
		
		//Admin widzi wszystko (narazie)
		showTechnicianTabs();
		showUserTabs();
	}

}

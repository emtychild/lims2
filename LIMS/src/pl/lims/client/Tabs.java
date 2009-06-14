package pl.lims.client;

import pl.lims.client.tabs.ConfigurationTab;
import pl.lims.client.tabs.IncidentsTab;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class Tabs extends LayoutContainer
{
	public Tabs()
	{
		TabPanel folder = new TabPanel();
		folder.setWidth(600);
		folder.setHeight(600);
		
		TabItem configTab = new TabItem("Configuration");
		configTab.add(new ConfigurationTab());
		folder.add(configTab);
		
		TabItem incidentsTab = new TabItem("Incidents");
		incidentsTab.add(new IncidentsTab());
		folder.add(incidentsTab);
		
		add(folder);
	}
}

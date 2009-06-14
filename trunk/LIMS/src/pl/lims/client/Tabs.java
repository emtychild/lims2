package pl.lims.client;

import pl.lims.client.tabs.ConfigurationTab;
import pl.lims.client.tabs.IncidentsTab;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class Tabs extends HorizontalPanel
{
	private TabPanel tabs = new TabPanel();

	@Override
	protected void onLoad()
	{
		tabs.setWidth("600px");

		tabs.add(new ConfigurationTab(), "Configuration", true);
		tabs.add(new IncidentsTab(), "Incidents", true);
		tabs.selectTab(0);
		
		this.add(tabs);
	}
}

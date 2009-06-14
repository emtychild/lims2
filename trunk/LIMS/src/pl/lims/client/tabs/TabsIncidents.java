package pl.lims.client.tabs;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class TabsIncidents extends HorizontalPanel
{
	@Override
	protected void onLoad()
	{
		this.add(new Label("Incidents"));
		super.onLoad();
	}
}

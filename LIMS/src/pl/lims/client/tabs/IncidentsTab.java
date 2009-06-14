package pl.lims.client.tabs;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class IncidentsTab extends HorizontalPanel
{
	@Override
	protected void onLoad()
	{
		this.add(new Label("Incidents"));
		super.onLoad();
	}
}

package pl.lims.client.tabs;

import pl.lims.client.forms.IncidentForm;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class IncidentsTab extends HorizontalPanel
{
	@Override
	protected void onLoad()
	{
		this.add(new IncidentForm());
		super.onLoad();
	}
}

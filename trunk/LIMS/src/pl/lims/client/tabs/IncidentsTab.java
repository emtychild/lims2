package pl.lims.client.tabs;

import pl.lims.client.forms.IncidentForm;
import pl.lims.client.forms.IncidentGrid;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class IncidentsTab extends VerticalPanel
{
	@Override
	protected void onLoad()
	{
		this.add(new IncidentForm());
		this.add(new IncidentGrid());
		super.onLoad();
	}
}

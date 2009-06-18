package pl.lims.client.tabs;

import pl.lims.client.Tabs;
import pl.lims.client.forms.IncidentForm;
import pl.lims.client.forms.IncidentGrid;

import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class IncidentsTab extends VerticalPanel
{
	Tabs tabs;
	
	public IncidentsTab(Tabs tabs)
	{
		this.tabs = tabs;
	}
	
	@Override
	protected void onLoad()
	{
		this.add(new IncidentForm(tabs));
		this.add(new IncidentGrid(tabs));
		super.onLoad();
	}
}

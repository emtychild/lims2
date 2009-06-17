package pl.lims.client.tabs;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class AboutTab extends HorizontalPanel
{
	@Override
	protected void onLoad()
	{
		this.add(new Label("About, bla bla bla"));
		super.onLoad();
	}
}
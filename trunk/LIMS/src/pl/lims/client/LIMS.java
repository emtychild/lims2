package pl.lims.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LIMS implements EntryPoint
{
	public void onModuleLoad()
	{
		RootPanel.get("center").add(new Tabs());
	}
}

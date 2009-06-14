package pl.lims.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IncidentManagerAsync
{
	void saveIncident(String name, String description, AsyncCallback<String> callback);
}

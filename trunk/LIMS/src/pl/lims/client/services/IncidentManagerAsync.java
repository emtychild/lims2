package pl.lims.client.services;

import java.util.List;

import pl.lims.client.common.model.Incident;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IncidentManagerAsync
{
	void saveIncident(String name, String description, AsyncCallback<String> callback);
	void addIncident(Incident i, AsyncCallback<String> callback);
	void getIncidents(AsyncCallback<List<Incident>> callback);
}


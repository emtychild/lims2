package pl.lims.client.services;

import java.util.List;

import pl.lims.client.common.model.Incident;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("imanager")
public interface IncidentManager extends RemoteService
{
	String saveIncident(String name, String description);
	String addIncident(Incident i);
	List<Incident> getIncidents();
	
}

package pl.lims.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("imanager")
public interface IncidentManager extends RemoteService
{
	String saveIncident(String name, String description);
}

package pl.lims.client.services;

import java.util.Collection;

import pl.lims.client.common.AddTagResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("kdb")
public interface KnowledgDBService extends RemoteService
{
	public AddTagResponse addTag(String name);
	public Collection<String> getTags();
}

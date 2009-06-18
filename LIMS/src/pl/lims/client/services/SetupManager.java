package pl.lims.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("setupManager")
public interface SetupManager extends RemoteService
{
	String addStatus(String name);
	String removeStatus(String name);
	List<String> getStatusNames();
	
	String addCategory(String name);
	String removeCategory(String name);
	List<String> getCategories();
}

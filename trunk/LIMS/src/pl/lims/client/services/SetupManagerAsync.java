package pl.lims.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SetupManagerAsync
{

	public void addStatus(String name, AsyncCallback<String> callback);

	public void getStatusNames(AsyncCallback<List<String>> callback);

}

package pl.lims.client.services;

import java.util.Collection;

import pl.lims.client.common.AddTagResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface KnowledgDBServiceAsync
{
	void addTag(String name, AsyncCallback<AddTagResponse> callback);
	void getTags(AsyncCallback<Collection<String>> callback);
}

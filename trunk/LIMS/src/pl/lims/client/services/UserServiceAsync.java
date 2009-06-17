package pl.lims.client.services;

import java.util.List;

import pl.lims.client.common.UserLoginRegisterResponse;
import pl.lims.client.common.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync
{
	public void login(String name, String password, AsyncCallback<UserLoginRegisterResponse> callback);
	public void register(String name, String password,String email, AsyncCallback<UserLoginRegisterResponse> callback);
	public void listUsers(AsyncCallback<List<User>> callback);
}

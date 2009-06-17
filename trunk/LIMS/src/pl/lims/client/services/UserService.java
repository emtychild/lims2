package pl.lims.client.services;

import java.util.List;

import pl.lims.client.common.UserLoginRegisterResponse;
import pl.lims.client.common.model.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService
{
	UserLoginRegisterResponse login(String name, String password);
	UserLoginRegisterResponse register(String name, String password, String email);
	List<User> listUsers();
}
package pl.lims.server;

import java.util.ArrayList;
import java.util.List;

import pl.lims.client.common.UserLoginRegisterResponse;
import pl.lims.client.common.model.User;
import pl.lims.client.common.model.UserType;
import pl.lims.client.services.UserService;
import pl.lims.server.database.dao.UserDAO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService
{
	private UserDAO userDAO = new UserDAO();
	private static final long	serialVersionUID	= -1196592120783416984L;

	@Override
	public UserLoginRegisterResponse login(String name, String password)
	{
		User user =  userDAO.loadUser(name, password);
		if(user == null)
			return new UserLoginRegisterResponse("Login or password incorrect");
		
		return new UserLoginRegisterResponse(user);
	}

	@Override
	public UserLoginRegisterResponse register(String name, String password, String email)
	{
		boolean loginAvailable = userDAO.userNameAvailable(name);
		if(!loginAvailable)
			return new UserLoginRegisterResponse("This name is already taken");
		
		User user = new User(name, password, email);
		
		if(name.equals("admin")) // Defaultowe konto admina :D
			user.setUserType(UserType.ADMIN);
		
		userDAO.saveUser(user);
		
		return new UserLoginRegisterResponse(user);
	}

	@Override
	public List<User> listUsers()
	{
		//TODO sprawdzanie uprawnien ;]
		List<User> res = new ArrayList<User>();
		res.addAll(userDAO.listUsers()); // to cos co zwraca persistent manager nie jest serializable
		
		return res;
	}

}

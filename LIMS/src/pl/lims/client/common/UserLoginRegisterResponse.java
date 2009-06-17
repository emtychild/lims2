package pl.lims.client.common;

import java.io.Serializable;

import pl.lims.client.common.model.User;

public class UserLoginRegisterResponse implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7712009631819962581L;
	
	private boolean success;
	private String message;
	private User user;
	
	public UserLoginRegisterResponse(){} // musi byc pusty 
	
	public UserLoginRegisterResponse(String message)
	{
		success = false;
		this.message = message;
	}
	public UserLoginRegisterResponse(User user)
	{
		success = true;
		this.user = user;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public String getMessage()
	{
		return message;
	}

	public User getUser()
	{
		return user;
	}
	
	
}

package pl.lims.client.common.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.extjs.gxt.ui.client.data.ModelData;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User implements Serializable, ModelData
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7752481397593343897L;

	@PrimaryKey
	private String				name;

	@Persistent
	private String				password;

	@Persistent
	private String				email;

	@Persistent
	private UserType			userType = UserType.REGULAR_USER;

	public User()
	{

	}

	public User(String name, String password, String email)
	{
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public String getPassword()
	{
		return password;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public String getEmail()
	{
		return email;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property)
	{
		if(property.equals("name"))
			return (X)name;
		else if(property.equals("email"))
			return (X)email;
		else if(property.equals("userType"))
			return (X)userType;
		return null;
	}

	@Override
	public Map<String, Object> getProperties()
	{
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name",name);
		properties.put("email", email);
		properties.put("userType",userType);
		return properties;
	}

	private static Collection<String> propertiesNames = new HashSet<String>();
	static
	{
		Collections.addAll(propertiesNames,"name","email","userType");
	}
	@Override
	public Collection<String> getPropertyNames()
	{
		return propertiesNames;
	}

	@Override
	public <X> X remove(String property)
	{
		// TODO Auto-generated method stub
		return null; //WTF ? 
	}

	@Override
	public <X> X set(String property, X value)
	{
		// TODO Auto-generated method stub
		return null; // olac ;]
	}

}

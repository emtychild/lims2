package pl.lims.client.common.model;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Category	implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6474974213064488399L;
	@PrimaryKey
	private String name;
	
	public Category(String name)
	{
		this.name = name;
	}
	public Category()
	{
		
	}
	public String getName()
	{
		return name;
	}
}

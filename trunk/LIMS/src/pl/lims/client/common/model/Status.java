package pl.lims.client.common.model;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Status implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@PrimaryKey
	private String						name;

	public Status(){}
	public Status(String name)
	{
		this.name  = name;
	}
	public String getName()
	{
		return name;
	}

}

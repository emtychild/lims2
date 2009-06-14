package pl.lims.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Urgency
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long	id;

	@Persistent
	String			urgency;

	public String getUrgency()
	{
		return urgency;
	}

	public void setUrgency(String urgency)
	{
		this.urgency = urgency;
	}

	public Long getId()
	{
		return id;
	}

}

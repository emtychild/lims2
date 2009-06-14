package pl.lims.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Incident
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long	id;

	@Persistent
	String			name;
	@Persistent
	String			description;
	@Persistent
	int				category;
	@Persistent
	int				statud_id;
	@Persistent
	int				impact_id;
	@Persistent
	int				urgency_id;

	public String getName()
	{
		return name;
	}

	public Long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}

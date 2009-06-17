package pl.lims.client.common.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class KnownIncident
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long	id;

	@Persistent
	private String	solution;

	public String getSolution()
	{
		return solution;
	}

	public void setSolution(String solution)
	{
		this.solution = solution;
	}

	public Long getId()
	{
		return id;
	}

}

package pl.lims.client.common.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.extjs.gxt.ui.client.data.ModelData;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Incident implements Serializable, ModelData
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long	id;

	@Persistent
	String			name;
	@Persistent
	String			description;
	@Persistent
	String				category;
	@Persistent
	String				status_id;
	@Persistent
	Long				impact_id;
	@Persistent
	Long				urgency_id;

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

	

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getStatus_id()
	{
		return status_id;
	}

	public void setStatus_id(String status_id)
	{
		this.status_id = status_id;
	}

	public Long getImpact_id()
	{
		return impact_id;
	}

	public void setImpact_id(Long impact_id)
	{
		this.impact_id = impact_id;
	}

	public Long getUrgency_id()
	{
		return urgency_id;
	}

	public void setUrgency_id(Long urgency_id)
	{
		this.urgency_id = urgency_id;
	}

	public <X> X get(String property)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getProperties()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<String> getPropertyNames()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public <X> X remove(String property)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public <X> X set(String property, X value)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

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
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

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
	String				status;
	@Persistent
	int				impact;
	@Persistent
	int				urgency;

	public Incident() {
		
	}
	
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
	
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getImpact()
	{
		return impact;
	}

	public void setImpact(int impact)
	{
		this.impact = impact;
	}

	public int getUrgency()
	{
		return urgency;
	}

	public void setUrgency(int urgency)
	{
		this.urgency = urgency;
	}

	@SuppressWarnings("unchecked")
	public <X> X get(String property)
	{
		if(property.equals("name")) return (X) name;
		if(property.equals("status"))return (X) status;
		if(property.equals("category"))return (X) category;
		if(property.equals("urgency"))return (X) new Integer( urgency);
		if(property.equals("impact"))return (X) new Integer(impact);
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

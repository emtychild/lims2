package pl.lims.client.etc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.data.ModelData;

public class ClientTagModel implements ModelData, Comparable<ClientTagModel>
{
	private String name;
	public ClientTagModel(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public <X> X get(String property)
	{
		if(property.equals("name"))
			return (X) name;
		
		return null;
	}

	public Map<String, Object> getProperties()
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("name", name);
		
		return res;
	}

	public Collection<String> getPropertyNames()
	{
		Set<String> res = new HashSet<String>();
		res.add("name");
		return res;
	}

	public <X> X remove(String property)
	{
		return null;
	}

	public <X> X set(String property, X value)
	{
		return null;
	}
	public int compareTo(ClientTagModel o)
	{
		return name.compareTo(o.name);
	}

}

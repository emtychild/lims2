package pl.lims.client.etc;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.data.ModelData;

public class SolutionModel implements ModelData
{
	private String tags;
	private String msg;
	private String date;
	
	
	public SolutionModel(String tags, String msg, String date)
	{
		super();
		this.tags = tags;
		this.msg = msg;
		this.date = date;
	}
	
	
	public String getTags()
	{
		return tags;
	}


	public String getMsg()
	{
		return msg;
	}


	public String getDate()
	{
		return date;
	}


	// #############################################################
	public <X> X get(String property)
	{
		if(property.equals("tags"))
			return (X)tags;
		else if(property.equals("msg"))
			return (X)msg;
		else if(property.equals("shortmsg"))
			return (X)(msg.substring(0, 20)+"...");
		else if(property.equals("date"))
			return (X)date;
		return null;
	}

	public Map<String, Object> getProperties()
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("tags", tags);
		res.put("msg", msg);
		res.put("shortmsg", msg.substring(0,20)+"...");
		res.put("date", date);
		return res;
	}

	public Collection<String> getPropertyNames()
	{
		Set<String> res = new HashSet<String>();
		Collections.addAll(res, "tags","msg","shortmsg","date");
		return res;
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

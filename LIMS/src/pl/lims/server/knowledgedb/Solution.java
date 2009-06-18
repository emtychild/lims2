package pl.lims.server.knowledgedb;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Solution 
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String desc;
	
	@Persistent
	private List<String> tags = new ArrayList<String>();

	public Solution(String desc, List<String> tags)
	{
		super();
		this.desc = desc;
		this.tags = tags;
	}

	public Long getId()
	{
		return id;
	}

	public String getDesc()
	{
		return desc;
	}

	public List<String> getTags()
	{
		return tags;
	}
	public void addTag(String tag)
	{
		tags.add(tag);
	}
}

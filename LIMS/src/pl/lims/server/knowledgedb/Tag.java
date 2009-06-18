package pl.lims.server.knowledgedb;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Tag
{
	@PrimaryKey
	private String name;
	
	@Persistent
	private List<Integer> solutions = new ArrayList<Integer>();
	
	public Tag(){}
	public Tag(String name)
	{
		this.name = name;
	}
	public Tag(String name, List<Integer> solutions)
	{
		this.name = name;
		this.solutions = solutions;
	}
	public String getName()
	{
		return name;
	}
	public List<Integer> getSolutions()
	{
		return solutions;
	}
}

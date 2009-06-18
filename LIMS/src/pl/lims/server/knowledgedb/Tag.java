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
	private List<Long> solutions = new ArrayList<Long>();
	
	public Tag(){}
	public Tag(String name)
	{
		this.name = name;
	}
	public Tag(String name, List<Long> solutions)
	{
		this.name = name;
		this.solutions = solutions;
	}
	public String getName()
	{
		return name;
	}
	public List<Long> getSolutions()
	{
		return solutions;
	}
	public void addSolutionId(long solId)
	{
		if(solutions == null)
			solutions = new ArrayList<Long>();
		solutions.add(solId);
	}
}

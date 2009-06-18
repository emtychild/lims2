package pl.lims.server.knowledgedb;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.lims.server.database.dao.KnowledgeDBDAO;

public class TagsManager
{
	private KnowledgeDBDAO db;
	private Map<String, Tag> tags = new HashMap<String, Tag>();
	
	public TagsManager(KnowledgeDBDAO db)
	{
		this.db = db;
		
		loadTags();
	}

	public boolean tagExists(String name)
	{
		return tags.containsKey(name);
	}
	


	public void addNewTag(String name)
	{
		Tag t = new Tag(name);
		tags.put(name, t);
		
		db.persist(t);
	}
	
	public Collection<String> tagNames()
	{
		return tags.keySet();
	}
    // ************************************
	private void loadTags()
	{
		System.out.println("TagsManager loading tags");
		
		List<Tag> dbTags = db.loadTags();
		System.out.println("Loaded "+dbTags.size()+" tags");
		
		for(Tag t: dbTags)
		{
			tags.put(t.getName(), t);
		}
	}
}

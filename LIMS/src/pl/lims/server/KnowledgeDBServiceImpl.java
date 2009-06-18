package pl.lims.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import pl.lims.client.common.AddTagResponse;
import pl.lims.client.services.KnowledgDBService;
import pl.lims.server.database.dao.KnowledgeDBDAO;
import pl.lims.server.knowledgedb.Solution;
import pl.lims.server.knowledgedb.Tag;
import pl.lims.server.knowledgedb.TagsManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class KnowledgeDBServiceImpl extends RemoteServiceServlet implements KnowledgDBService
{
	private static final long	serialVersionUID	= -3494539127367593323L;

	private KnowledgeDBDAO db = new KnowledgeDBDAO();
	private TagsManager tagsManager = new TagsManager(db);
	
	public AddTagResponse addTag(String name)
	{
		if(tagsManager.tagExists(name))
		{
			return new AddTagResponse("This tag already exists", false);
		}
		
		tagsManager.addNewTag(name);
		return new AddTagResponse("Tag successfuly added", true);
	}

	public Collection<String> getTags()
	{
		Collection<String> res = new HashSet<String>();
		res.addAll(tagsManager.tagNames());
		return res;
	}

	public boolean addSolution(String desc, List<String> tags)
	{
		try{
			Solution solution = new Solution(desc, tags);
			db.persist(solution);
			
			long id = solution.getId();
			
			for(String tagName: tags)
			{
				Tag tag = tagsManager.getTag(tagName);
				solution.addTag(tagName);
				tag.addSolutionId(id);
				db.persist(tag);
			}
			
			db.persist(solution);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}

package pl.lims.server.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import pl.lims.server.database.PMF;
import pl.lims.server.knowledgedb.Tag;

public class KnowledgeDBDAO
{
	private PersistenceManagerFactory	pmf	= PMF.get();

	
	public List<Tag> loadTags()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			Query q = pm.newQuery(Tag.class);
			List<Tag> result = new ArrayList<Tag>();
			result.addAll((List<Tag>)q.execute());
			return result;
		}
		finally
		{
			pm.close();
		}
	}


	public void persist(Object t)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			pm.makePersistent(t);
		}
		finally
		{
			pm.close();
		}
	}
	
	
}

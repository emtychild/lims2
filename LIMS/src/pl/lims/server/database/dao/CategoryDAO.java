package pl.lims.server.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import pl.lims.client.common.model.Category;
import pl.lims.client.common.model.Status;
import pl.lims.server.database.PMF;

public class CategoryDAO
{
	private PersistenceManagerFactory	pmf	= PMF.get();

	public List<String> listCategories()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery(Category.class);
		List<String> names = new ArrayList<String>();
		try
		{
			List<Category> categoryList = (List<Category>) q.execute();
			for (Category s : categoryList)
			{
				names.add(s.getName());
			}
		}
		catch (Exception ex)
		{
			names.add("cannot retrieve categories list");
			ex.printStackTrace();
		}
		finally
		{
			pm.close();
		}
		return names;
	}

	public String addCategory(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Category s = new Category(name);

		Query q = pm.newQuery(Category.class);
		q.setFilter("name == nameParam");
		q.declareParameters("String nameParam");

		try
		{
			List<Category> existing = (List<Category>) q.execute(name);
			if (existing.isEmpty())
			{
				pm.makePersistent(s);
				return "Category successfuly added";
			}
			else
			{
				return "Such category already exists";
			}
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
	}

	public String removeCategory(String name)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Category s = new Category(name);

		Query q = pm.newQuery(Category.class);
		q.setFilter("name == nameParam");
		q.declareParameters("String nameParam");

		try
		{
			List<Category> existing = (List<Category>) q.execute(name);
			if (existing.isEmpty())
			{
				return "There is no such item oO";
			}
			else
			{
				Category st = existing.get(0);
				pm.deletePersistent(st);
				return "Category removed";
			}
		}
		catch (Throwable ex)
		{
			return ex.getMessage();
		}
		finally
		{
			pm.close();
		}
	}
}
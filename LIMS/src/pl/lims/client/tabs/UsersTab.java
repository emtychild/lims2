package pl.lims.client.tabs;

import java.util.ArrayList;
import java.util.List;

import pl.lims.client.common.model.Incident;
import pl.lims.client.common.model.User;
import pl.lims.client.services.UserService;
import pl.lims.client.services.UserServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UsersTab extends LayoutContainer
{
	private final UserServiceAsync	userService	= GWT.create(UserService.class);
	
	public UsersTab()
	{
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId("name");  
	    column.setHeader("Name");  
	    column.setWidth(200);  
	    configs.add(column);
	      
	    column = new ColumnConfig();  
	    column.setId("status");  
	    column.setHeader("Status");  
	    column.setWidth(200);  
	    configs.add(column);  
	    
	    ColumnModel cm = new ColumnModel(configs);  
	    
	    final ListStore<User> store = new ListStore<User>();  
	    
		userService.listUsers(new AsyncCallback<List<User>>() {
			public void onFailure(Throwable caught)
			{
				Info.display("Error while getting users list", caught.getMessage());
			}

			public void onSuccess(java.util.List<User> result)
			{
				System.out.println("rozmiar wyniku " + result.size());
				store.add(result);
			}
		});

		 ContentPanel cp = new ContentPanel();  
		    cp.setBodyBorder(false);  
		    cp.setHeading("Users");  
		    //cp.setButtonAlign(HorizontalAlignment.CENTER);  
		    cp.setLayout(new FitLayout());  
		    cp.setSize(600, 300);  
		  
		    Grid<User> grid = new Grid<User>(store, cm);  
		    grid.setStyleAttribute("borderTop", "none");  
		    grid.setAutoExpandColumn("name");  
		    grid.setBorders(true);  
		    cp.add(grid);  
		  
		    add(cp);  
	}
}

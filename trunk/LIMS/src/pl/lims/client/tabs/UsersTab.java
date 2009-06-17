package pl.lims.client.tabs;

import java.util.ArrayList;
import java.util.List;

import pl.lims.client.common.model.User;
import pl.lims.client.services.UserService;
import pl.lims.client.services.UserServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UsersTab extends ContentPanel
{
	private final UserServiceAsync	userService	= GWT.create(UserService.class);
	
	ContentPanel cp = new ContentPanel();
	ColumnModel cm;
	
	public UsersTab()
	{
		setHeading("Users list");
		setLayout(new RowLayout(Orientation.HORIZONTAL)); 
		
		 List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		   
	     ColumnConfig column = new ColumnConfig();  
	     column.setId("name");  
	     column.setHeader("Name");  
	     column.setWidth(200);  
	     configs.add(column);  
	   
	     column = new ColumnConfig();  
	     column.setId("userType");  
	     column.setHeader("User Type");  
	     column.setWidth(100);  
	     configs.add(column);  
	     
	     
	     cm = new ColumnModel(configs);  
	   
	       
	     cp.setBodyBorder(false);  
	     cp.setButtonAlign(HorizontalAlignment.CENTER);  
	     cp.setLayout(new FitLayout());  
	  //   cp.setSize(600, 300);  
	   
	          
	     add(cp);

	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		
		userService.listUsers(new AsyncCallback<List<User>>(){
			public void onFailure(Throwable caught)
			{
				Info.display("Error while getting users list", caught.getMessage());
			}
			public void onSuccess(java.util.List<User> result)
			{
				System.out.println("rozmiar wyniku "+result.size());
				
				ListStore<User> store = new ListStore<User>();  
			     store.add(result);  
			   
			     Grid<User> grid = new Grid<User>(store, cm);  
			     grid.setStyleAttribute("borderTop", "none");  
			     grid.setAutoExpandColumn("name");  
			     grid.setBorders(true);  
			     
			     cp.add(grid); // czemu to cholerstwo nie wyswietla ;/
			     cp.repaint();
			}
		});
	}
}

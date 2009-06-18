package pl.lims.client.forms;

import java.util.ArrayList;
import java.util.List;

import pl.lims.client.common.model.Incident;
import pl.lims.client.services.IncidentManager;
import pl.lims.client.services.IncidentManagerAsync;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IncidentGrid extends LayoutContainer
{
	private final IncidentManagerAsync incidentManager = GWT.create(IncidentManager.class);
	
	public IncidentGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId("name");  
	    column.setHeader("Name");  
	    column.setWidth(100);  
	    configs.add(column);
	      
	    column = new ColumnConfig();  
	    column.setId("category");  
	    column.setHeader("Category");  
	    column.setWidth(100);  
	    configs.add(column);  
	    
	    column = new ColumnConfig();  
	    column.setId("status");  
	    column.setHeader("Status");  
	    column.setWidth(100);  
	    configs.add(column);  
	    
	    column = new ColumnConfig();  
	    column.setId("urgency");  
	    column.setHeader("Urgency");  
	    column.setWidth(100);  
	    configs.add(column);  
	    
	    column = new ColumnConfig();  
	    column.setId("impact");  
	    column.setHeader("Impact");  
	    column.setWidth(100);  
	    configs.add(column);  
	    
	    ColumnModel cm = new ColumnModel(configs);  
	    
	    final ListStore<Incident> store = new ListStore<Incident>();  
	    
	    
	    incidentManager.getIncidents(new AsyncCallback<List<Incident>>() {

			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(List<Incident> result)
			{
				store.add(result);
			}
	    });
	    
	    ContentPanel cp = new ContentPanel();  
	    cp.setBodyBorder(false);  
	    cp.setHeading("Incidents list");  
	    //cp.setButtonAlign(HorizontalAlignment.CENTER);  
	    cp.setLayout(new FitLayout());  
	    cp.setSize(600, 200);  
	  
	    Grid<Incident> grid = new Grid<Incident>(store, cm);  
	    grid.setStyleAttribute("borderTop", "none");  
	    grid.setAutoExpandColumn("name");  
	    grid.setBorders(true);  
	    cp.add(grid);  
	  
	    add(cp);  
	}
}

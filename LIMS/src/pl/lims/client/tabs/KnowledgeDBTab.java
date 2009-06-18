package pl.lims.client.tabs;


import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import pl.lims.client.common.AddTagResponse;
import pl.lims.client.etc.ClientTagModel;
import pl.lims.client.etc.SolutionModel;
import pl.lims.client.etc.SolutionTestData;
import pl.lims.client.services.KnowledgDBService;
import pl.lims.client.services.KnowledgDBServiceAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.dnd.ListViewDragSource;
import com.extjs.gxt.ui.client.dnd.ListViewDropTarget;
import com.extjs.gxt.ui.client.dnd.DND.Operation;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class KnowledgeDBTab extends LayoutContainer
{
	private final KnowledgDBServiceAsync	kdbService	= GWT.create(KnowledgDBService.class);

	private List<ClientTagModel>		tags = new ArrayList<ClientTagModel>();
	private ListStore<ClientTagModel> tagsListStore;
	
	private ListStore<ClientTagModel> addSolutionTagStore;
	
	ListStore<SolutionModel> gridStore;
	
	public KnowledgeDBTab()
	{

	}
	//@Override
	protected void onLoad()
	{
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setFrame(false);
		panel.setHeaderVisible(false);
		panel.setHeight(590);
		final BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);

		ContentPanel north = new ContentPanel();
		ContentPanel west = new ContentPanel();
		west.setScrollMode(Scroll.AUTOY);
	
		ContentPanel center = new ContentPanel();

		initAddForm(north);
		initTagsList(west);
		initSolutionsGrid(center);

		north.setHeading("Add new Solution");
		west.setHeading("Tags");
		center.setHeading("Solution list");
		center.setScrollMode(Scroll.AUTOX);

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 200);
		northData.setCollapsible(true);
		northData.setFloatable(true);
		northData.setSplit(true);
		northData.setMargins(new Margins(5, 5, 0, 5));

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(5));

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(5, 5, 5, 0));

		panel.add(north, northData);
		panel.add(west, westData);
		panel.add(center, centerData);

		setLayout(new FitLayout());
		add(panel);
	}
	private void initSolutionsGrid(ContentPanel center)
	{
		center.setLayout(new RowLayout(Orientation.VERTICAL));
		
		TextBox tagFilter = new TextBox();
		tagFilter.setText("hardware,printer");
		tagFilter.setWidth("400px");
		
		
		
		 // add paging support for a local collection of models  
	     PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(SolutionTestData.getSolutions());  
	   
	     // loader  
	     PagingLoader loader = new BasePagingLoader(proxy);  
	     loader.setRemoteSort(true);  
	   
	     gridStore = new ListStore<SolutionModel>(loader); 
	   
	     final PagingToolBar toolBar = new PagingToolBar(10);  
	     toolBar.bind(loader);  
	   
	     loader.load(0, 10);  
	   
	   
	     List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
	   
	     ColumnConfig column = new ColumnConfig();  
	     column.setId("tags");  
	     column.setHeader("Tags");  
	     column.setWidth(140);  
	     configs.add(column);  
	   
	     column = new ColumnConfig();  
	     column.setId("shortmsg");  
	     column.setHeader("Beginning");  
	     column.setWidth(160);  
	     configs.add(column);  

	   
	     column = new ColumnConfig("date", "Added", 130);  
	     column.setAlignment(HorizontalAlignment.RIGHT);  
	     configs.add(column);  
	   
	     ColumnModel cm = new ColumnModel(configs);  
	   
	     ContentPanel cp = new ContentPanel();  
	     cp.setFrame(true);  
	     cp.setHeading("Known solutions for incidents");  
	     cp.setButtonAlign(HorizontalAlignment.CENTER);  
	     cp.setLayout(new FitLayout());  
	     cp.setBottomComponent(toolBar);  
	     cp.setWidth(430);
	     cp.setAutoHeight(true);
	   
	     final Grid<SolutionModel> grid = new Grid<SolutionModel>(gridStore, cm);  
	     grid.setBorders(true);  
	     grid.setAutoExpandColumn("tags");  
	     
	     Menu contextMenu = new Menu();
			MenuItem remove = new MenuItem();
			remove.setText("Show Details");
			remove.addSelectionListener(new SelectionListener<MenuEvent>() {
				@Override
				public void componentSelected(MenuEvent ce)
				{
		    		SolutionModel selected = grid.getSelectionModel().getSelectedItem();
		    		
		    		Window window = new Window();
		    		window.setSize(400, 300);
		    		window.setClosable(true);
		    		window.setHeading("Solution details");
		    		window.setLayout(new RowLayout(Orientation.VERTICAL));
		    		window.setFrame(true);
		    		
		    	     Text label1 = new Text(selected.getTags());  
		    	     label1.addStyleName("pad-text");  
		    	     label1.setStyleAttribute("backgroundColor", "white");  
		    	     label1.setBorders(true);  
		    	   
		    	     Text label2 = new Text(selected.getMsg());  
		    	     label2.addStyleName("pad-text");  
		    	     label2.setStyleAttribute("backgroundColor", "white");  
		    	     label2.setBorders(true);  
		    	   
		    	     Text label3 = new Text(selected.getDate());  
		    	     label3.addStyleName("pad-text");  
		    	     label3.setStyleAttribute("backgroundColor", "white");  
		    	     label3.setBorders(true);  
		    	   
		    	     window.add(label1, new RowData(1, -1, new Margins(4)));  
		    	     window.add(label2, new RowData(1, 1, new Margins(0, 4, 0, 4)));  
		    	     window.add(label3, new RowData(1, -1, new Margins(4)));  
		    		
		    		window.show();
				}
			});
			contextMenu.add(remove);
		grid.setContextMenu(contextMenu);
	   
	     cp.add(grid);  
		
		
		
		
		center.add(tagFilter);
		center.add(cp);
		
	}

	private void initTagsList(ContentPanel west)
	{
		loadTagsFromServer();

		west.setLayout(new RowLayout(Orientation.VERTICAL));

		HorizontalPanel addTagPanel = new HorizontalPanel();
		final TextBox addTagTB = new TextBox();
		addTagTB.setWidth("90px");
		Button addTabButton = new Button("Add");
		addTabButton.setWidth(45);
		addTagPanel.add(addTagTB);
		addTagPanel.add(addTabButton);

		ListView<ClientTagModel> list1 = new ListView<ClientTagModel>();
		list1.setDisplayProperty("name");
		tagsListStore = new ListStore<ClientTagModel>();
		tagsListStore.setStoreSorter(new StoreSorter<ClientTagModel>());
		tagsListStore.add(tags);
		list1.setStore(tagsListStore);

		ListViewDragSource dragSource = new ListViewDragSource(list1);
		dragSource.setGroup("NewSolution");

		addTabButton.addListener(Events.Select, new SelectionListener<ComponentEvent>() {
			@Override
			public void componentSelected(ComponentEvent ce)
			{
				final String newTag = addTagTB.getValue();
				if (newTag == null || newTag.equals(""))
				{
					MessageBox.alert("Adding Tag", "Tag must not be empty", null);
					return;
				}

				kdbService.addTag(newTag, new AsyncCallback<AddTagResponse>() {
					public void onFailure(Throwable caught)
					{
						MessageBox.alert("Error!", "Error while adding new tag", null);
					}

					public void onSuccess(AddTagResponse result)
					{
						if (!result.isSuccess())
						{
							Info.display("Tag adding", result.getMsg());
							return;
						}

						Info.display("Tag adding", "Tag "+newTag+" added successfuly");
						String tag = newTag;
						ClientTagModel ctm = new ClientTagModel(tag);
						tags.add(ctm);
						tagsListStore.add(ctm);
					}
				});
			}
		});

		west.add(addTagPanel);
		west.add(list1);
	}

	private void loadTagsFromServer()
	{
		kdbService.getTags(new AsyncCallback<Collection<String>>(){
			public void onFailure(Throwable caught) 
			{
				Info.display("Error",caught.getMessage());
			};
			public void onSuccess(Collection<String> result)
			{
				 for(String str: result)
				 {
					 ClientTagModel ctm = new ClientTagModel(str);
					 tags.add(ctm);
				 }
				 tagsListStore.add(tags);
			};
		});
	}

	private void initAddForm(ContentPanel north)
	{
		north.setLayout(new RowLayout(Orientation.HORIZONTAL));
 
			VerticalPanel tagListPanel = new VerticalPanel();
			tagListPanel.setHeight(160);
			tagListPanel.setWidth(150);
			tagListPanel.setScrollMode(Scroll.AUTOY);
			tagListPanel.setStyleAttribute("bgcolor", "#DDDD");
			
			tagListPanel.add(new Label("Drag tags here"));
			
			final ListView<ClientTagModel> list1 = new ListView<ClientTagModel>();
			list1.setDisplayProperty("name");
			list1.setHeight(110);
			addSolutionTagStore = new ListStore<ClientTagModel>();
			addSolutionTagStore.setStoreSorter(new StoreSorter<ClientTagModel>());
			list1.setStore(addSolutionTagStore);
			tagListPanel.add(list1);
			
			ListViewDropTarget dropTarget = new ListViewDropTarget(list1);
			dropTarget.setOperation(Operation.COPY);
			dropTarget.setGroup("NewSolution");
			
			
			Button remove = new Button("remove selected");
			remove.addListener(Events.Select, new SelectionListener<ComponentEvent>(){
				@Override
				public void componentSelected(ComponentEvent ce)
				{
					ClientTagModel ctm = list1.getSelectionModel().getSelectedItem();
					if(ctm == null)
						return;
					
					addSolutionTagStore.remove(ctm);
				}
			});
			remove.setWidth(150);
			tagListPanel.add(remove);

			VerticalPanel solutionDescPanel = new VerticalPanel();
			solutionDescPanel.setHeight(160);
			solutionDescPanel.setWidth(450);
			
			solutionDescPanel.add(new Label("Write description"));
			final TextArea area = new TextArea();
			area.setWidth(400);
			area.setHeight(110);
			solutionDescPanel.add(area);
			
			Button sendB = new Button("Add solution");
			sendB.setWidth(400);
			solutionDescPanel.add(sendB);
			
			sendB.addListener(Events.Select, new SelectionListener<ComponentEvent>(){
			@Override
				public void componentSelected(ComponentEvent ce)
				{
					try
					{
						List<String> tags = new ArrayList<String>();
						String desc = area.getValue();
						//SimpleDateFormat sdf = new SimpleDateFormat("Y/M/d HH:mm");
						String date = "09/06/18 14:00";//sdf.format(GregorianCalendar.getInstance());
						String tagsS = "";
						for(int i = 0; i < addSolutionTagStore.getCount(); i++)
						{
							tags.add(addSolutionTagStore.getAt(i).getName());
							
						}
						
						if(addSolutionTagStore.getCount() > 0)
							tagsS = addSolutionTagStore.getAt(0).getName();
						
						for(int i = 1; i < addSolutionTagStore.getCount(); i++)
						{
							tagsS += ","+addSolutionTagStore.getAt(i).getName();
						}
						SolutionModel sm = new SolutionModel(tagsS, desc, date);
						
						gridStore.add(sm);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
	/*				kdbService.addSolution(desc, tags, new AsyncCallback<Boolean>(){
						public void onFailure(Throwable caught)
						{
							MessageBox.alert("Error!", caught.getMessage(),null);
						}
						public void onSuccess(Boolean result)
						{
							if(!result)
							{
								Info.display("Solution adding","Solution couln't be added");
								return;
							}
							//TODO dodac do listy
							Info.display("Solution adding", "Solution added");
						}
					}); */
				}	
			});
			
		north.add(tagListPanel);
		north.add(solutionDescPanel);
	}
}

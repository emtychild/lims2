package pl.lims.client.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.lims.client.common.AddTagResponse;
import pl.lims.client.etc.ClientTagModel;
import pl.lims.client.services.KnowledgDBService;
import pl.lims.client.services.KnowledgDBServiceAsync;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.dnd.ListViewDragSource;
import com.extjs.gxt.ui.client.dnd.ListViewDropTarget;
import com.extjs.gxt.ui.client.dnd.DND.Operation;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
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

		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 220);
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
		// TODO Auto-generated method stub

	}

	private void initTagsList(ContentPanel west)
	{
		loadTagsFromServer();

		setLayout(new RowLayout(Orientation.VERTICAL));

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
				if (newTag == null || !newTag.equals(""))
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
			area.setHeight(100);
			solutionDescPanel.add(area);
			
			Button sendB = new Button("Add");
			solutionDescPanel.add(sendB);
			
			sendB.addListener(Events.Select, new SelectionListener<ComponentEvent>(){
			@Override
				public void componentSelected(ComponentEvent ce)
				{
					List<String> tags = new ArrayList<String>();
					for(int i = 0; i < addSolutionTagStore.getCount(); i++)
					{
						//TODO
					}
				}	
			});
			
		north.add(tagListPanel);
		north.add(solutionDescPanel);
	}
}

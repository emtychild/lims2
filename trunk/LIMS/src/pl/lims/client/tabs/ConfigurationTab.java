package pl.lims.client.tabs;

import java.util.List;

import pl.lims.client.services.SetupManager;
import pl.lims.client.services.SetupManagerAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfigurationTab extends VerticalPanel
{
	private final SetupManagerAsync	setupManager	= GWT.create(SetupManager.class);
	private final ListBox			statusNames		= new ListBox();
	private final DialogBox			dialogBox		= new DialogBox();

	@Override
	protected void onLoad()
	{
		add(new Label("Setp management - add incident status:"));
		
		final TextBox status = new TextBox();
		final Button addButton = new Button("Add status");

		final Button closeButton = new Button("Close");
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event)
			{
				dialogBox.hide();
			}
		});
		dialogBox.add(closeButton);

		addButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{
				setupManager.addStatus(status.getText(), new AsyncCallback<String>() {

					public void onFailure(Throwable caught)
					{
						dialogBox.setText(caught.getMessage());
						dialogBox.show();

					}

					public void onSuccess(String result)
					{
						dialogBox.setText(result);
						dialogBox.show();

						loadStatusList();
					}

				});
			}

		});

		loadStatusList();
		
		add(status);
		add(addButton);
		add(statusNames);
	}
	
	private void loadStatusList()
	{
		setupManager.getStatusNames(new AsyncCallback<List<String>>() {
			public void onFailure(Throwable caught)
			{
				dialogBox.setText(caught.getMessage());
				dialogBox.show();
			}

			public void onSuccess(List<String> result)
			{
				statusNames.clear();
				for (String name : result)
				{
					statusNames.addItem(name);
				}
			}
		});
	}
}

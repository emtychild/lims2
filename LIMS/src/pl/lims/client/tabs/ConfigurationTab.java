package pl.lims.client.tabs;

import java.util.List;

import pl.lims.client.services.SetupManager;
import pl.lims.client.services.SetupManagerAsync;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class ConfigurationTab extends VerticalPanel
{
	private final SetupManagerAsync	setupManager	= GWT.create(SetupManager.class);
	private final ListBox			statusNames		= new ListBox();
	private final Dialog dialogBox = new Dialog();
	
	public ConfigurationTab()
	{
		dialogBox.setButtons(Dialog.OK);
		dialogBox.setHideOnButtonClick(true);
		dialogBox.setAutoHeight(true);
	}

	@Override
	protected void onLoad()
	{

		add(new Label("Setup management - add incident status:"));

		final TextBox status = new TextBox();
		final Button addButton = new Button("Add status");

		addButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce)
			{
				setupManager.addStatus(status.getText(), new AsyncCallback<String>() {

					public void onFailure(Throwable caught)
					{
						showDialog("Error!", caught.getMessage());
					}

					public void onSuccess(String result)
					{
						showDialog("Success!",result);

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
				showDialog("Error!", caught.getMessage());
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

	private void showDialog(String title, String msg)
	{
		dialogBox.setHeading(title);
		dialogBox.addText(msg);
		
		dialogBox.show();
	}
}

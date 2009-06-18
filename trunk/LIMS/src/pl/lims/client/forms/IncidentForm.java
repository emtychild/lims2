package pl.lims.client.forms;

import pl.lims.client.Tabs;
import pl.lims.client.common.model.Incident;
import pl.lims.client.services.IncidentManager;
import pl.lims.client.services.IncidentManagerAsync;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IncidentForm extends LayoutContainer
{
	private VerticalPanel	vp;
	private final IncidentManagerAsync incidentManager = GWT.create(IncidentManager.class);
	Tabs tabs;
	
	public IncidentForm(Tabs tabs)
	{
		this.tabs = tabs;
		vp = new VerticalPanel();
		vp.setSpacing(10);
	}

	@Override
	protected void onRender(Element parent, int index)
	{
		super.onRender(parent, index);
		createForm1();
		// createForm2();
		add(vp);
	}

	private void createForm1()
	{
		FormPanel simple = new FormPanel();
		simple.setHeading("Incident Report");
		simple.setFrame(true);
		simple.setWidth(350);

		final TextField<String> incidentName = new TextField<String>();
		incidentName.setFieldLabel("Name");
		incidentName.setAllowBlank(false);
		simple.add(incidentName);

		final SimpleComboBox<String> categoryCB = new SimpleComboBox<String>();
		categoryCB.add("test 1");
		categoryCB.setFieldLabel("Category");
		categoryCB.setName("Impact");
		simple.add(categoryCB);
		
		final SimpleComboBox<String> statusCB = new SimpleComboBox<String>();
		statusCB.add("proceeding");
		statusCB.setFieldLabel("Status");
		statusCB.setName("Status");
		simple.add(statusCB);

		final SimpleComboBox<String> urgencyCB = new SimpleComboBox<String>();
		for(int i=0; i<5; i++) {
			urgencyCB.add(String.valueOf(i));
		}
		urgencyCB.setWidth(15);
		urgencyCB.setFieldLabel("Urgency");
		urgencyCB.setName("Urgency");
		simple.add(urgencyCB);

		final SimpleComboBox<String> impactCB = new SimpleComboBox<String>();
		for(int i=0; i<5; i++) {
			impactCB.add(String.valueOf(i));
		}
		impactCB.setFieldLabel("Impact");
		impactCB.setName("Impact");
		impactCB.setWidth(15);
		simple.add(impactCB);

		final TextArea description = new TextArea();
		description.setPreventScrollbars(true);
		description.setFieldLabel("Description");
		description.setHeight(200);
		simple.add(description);

		
		Button sendButton = new Button("Report incident");
		sendButton.addListener(Events.Select, new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce)
			{
				Incident incident = new Incident();
				incident.setName(incidentName.getValue());
				incident.setCategory(categoryCB.getSelection().get(categoryCB.getSelectedIndex()).getValue());
				incident.setStatus(statusCB.getSelection().get(statusCB.getSelectedIndex()).getValue());
				incident.setUrgency(urgencyCB.getSelectedIndex()+1);
				incident.setImpact(impactCB.getSelectedIndex()+1);
				incident.setDescription(description.getValue());
				incident.setUserName(tabs.getUser().getName());
				
				incidentManager.addIncident(incident, new AsyncCallback<String>() {
					public void onFailure(Throwable caught)
					{
						Dialog d = new Dialog();
						d.addText(caught.getMessage());
						d.show();
					}
					public void onSuccess(String result)
					{
						Dialog d = new Dialog();
						d.addText(result);
						d.show();
					}
				});
			}
			
		});
		simple.add(sendButton);
		vp.add(simple);
	}
	
	private void createForm2()
	{
		FormPanel form2 = new FormPanel();
		form2.setFrame(true);
		form2.setHeading("Simple Form with FieldSets");
		form2.setWidth(350);
		form2.setLayout(new FlowLayout());

		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading("User Information");
		fieldSet.setCheckboxToggle(true);

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(75);
		fieldSet.setLayout(layout);

		TextField<String> firstName = new TextField<String>();
		firstName.setFieldLabel("First Name");
		firstName.setAllowBlank(false);
		fieldSet.add(firstName);

		TextField<String> lastName = new TextField<String>();
		lastName.setFieldLabel("Last Name");
		fieldSet.add(lastName);

		TextField<String> company = new TextField<String>();
		company.setFieldLabel("Company");
		fieldSet.add(company);

		TextField<String> email = new TextField<String>();
		email.setFieldLabel("Email");
		fieldSet.add(email);

		form2.add(fieldSet);

		fieldSet = new FieldSet();
		fieldSet.setHeading("Phone Numbers");
		fieldSet.setCollapsible(true);

		layout = new FormLayout();
		layout.setLabelWidth(75);
		fieldSet.setLayout(layout);

		TextField<String> field = new TextField<String>();
		field.setFieldLabel("Home");
		fieldSet.add(field);

		field = new TextField<String>();
		field.setFieldLabel("Business");
		fieldSet.add(field);

		field = new TextField<String>();
		field.setFieldLabel("Mobile");
		fieldSet.add(field);

		field = new TextField<String>();
		field.setFieldLabel("Fax");
		fieldSet.add(field);

		form2.add(fieldSet);
		form2.setButtonAlign(HorizontalAlignment.CENTER);
		form2.addButton(new Button("Save"));
		form2.addButton(new Button("Cancel"));

		vp.add(form2);
	}

}

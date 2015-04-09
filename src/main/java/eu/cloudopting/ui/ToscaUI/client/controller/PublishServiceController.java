package eu.cloudopting.ui.ToscaUI.client.controller;


import org.cruxframework.crux.core.client.controller.Controller;
import org.cruxframework.crux.core.client.controller.Expose;
import org.cruxframework.crux.core.client.ioc.Inject;
import org.cruxframework.crux.core.client.rest.Callback;
import org.cruxframework.crux.core.client.screen.views.BindView;
import org.cruxframework.crux.core.client.screen.views.WidgetAccessor;
import org.cruxframework.crux.widgets.client.formdisplay.FormDisplay;
import org.cruxframework.crux.widgets.client.select.SingleSelect;
import org.cruxframework.crux.widgets.client.textarea.TextArea;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import eu.cloudopting.ui.ToscaUI.client.remote.IProxyAPIService;
import eu.cloudopting.ui.ToscaUI.client.utils.Navigate;

/**
 * 
 * @author xeviscc
 *
 */
@Controller("publishServiceController")
public class PublishServiceController extends AbstractController
{
	@Inject
	public PublishServiceView view;

	@Inject
	public IProxyAPIService api;
	
	private final static String PAGE_NAME = "Publish Service";
	
	@Expose
	public void onLoad() {
		buildView();
	}

	@Expose
	public void itemSelected() {
	}
	
	@BindView("publishService")
	public static interface PublishServiceView extends WidgetAccessor
	{
		HTMLPanel panelScreen();
		SingleSelect singleSelect();
	}

	/*
	 * PRIVATE METHODS
	 */
	
	private TextBox serviceName = new TextBox();
	private TextArea serviceDescription = new TextArea();
	private TextBox textUpload = new TextBox();
	private FileUpload upload = new FileUpload();
	
	private void buildView() {
		setScreenHeader(view.panelScreen(), PAGE_NAME);
		
		FormDisplay formDisplay = new FormDisplay();
		formDisplay.setStyleName("publishService-Form");
		view.panelScreen().add(formDisplay);
		
		
		serviceName.setWidth("630px");
		formDisplay.addEntry("Service Name", serviceName, HasHorizontalAlignment.ALIGN_DEFAULT);
		
		
		serviceDescription.setHeight("243px");
		serviceDescription.setWidth("630px");
		formDisplay.addEntry("Service Description", serviceDescription, HasHorizontalAlignment.ALIGN_DEFAULT);
		
		//Set file upload
		buildFileUpload(formDisplay);
		
		//Set Content Library section
		buildContentLibrary(formDisplay);

		//Set Bottom buttons
		buildBottomButtons(formDisplay);
		
		//
	}

	private void buildFileUpload(FormDisplay formDisplay) {
		HTMLPanel aux2 = new HTMLPanel("");
		
		textUpload.setName("textUpload");
		textUpload.setWidth("51%");
		
		aux2.add(textUpload);
		
		upload.setName("Choose Tosca Template Upload");
		upload.setStyleName("publish-upload");
		upload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				textUpload.setText(upload.getFilename().replace("C:\\fakepath\\", ""));
				
//				File f = new File(upload.getFilename());
//				FlatMessageBox.show(upload.getElement()., MessageType.INFO);
//				((TextBox) view.panelScreen().getElementById("textUpload")).setText("HOLA");
			}
		});
		
		aux2.add(upload);
		
		formDisplay.addEntry("", aux2, HasHorizontalAlignment.ALIGN_DEFAULT);
	}

	private void buildContentLibrary(FormDisplay formDisplay) {
		Label labelCL = new Label();
		labelCL.setText("Content Library");
		formDisplay.addEntry("", labelCL, HasHorizontalAlignment.ALIGN_DEFAULT);
		
		HTMLPanel contentLibraryPanel = new HTMLPanel("");
		
		SingleSelect singleSelect = view.singleSelect();
		singleSelect.addItem("Item One", "value");
		singleSelect.addItem("Item Two", "value");
		singleSelect.addItem("Item Three", "value");
		singleSelect.addItem("Item Four", "value");
		contentLibraryPanel.add(singleSelect);

		//Set vertical buttons.
		buildVerticalButtons(contentLibraryPanel);
		
		formDisplay.addEntry("",  contentLibraryPanel, HasHorizontalAlignment.ALIGN_DEFAULT);
	}

	private void buildVerticalButtons(HTMLPanel panel) {
		
		HTMLPanel panelVerticalButton = new HTMLPanel("");
		panelVerticalButton.setStyleName("panelVerticalButton");
		
		Button buttonAdd = new Button();
		buttonAdd.setText("Add Item");
		buttonAdd.setStyleName("crux-Button publish-Library");
		buttonAdd.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addItem();				
			}
		});
		panelVerticalButton.add(buttonAdd);
		
		Button buttonDelete = new Button();
		buttonDelete.setText("Delete Item");
		buttonDelete.setStyleName("crux-Button publish-Library");
		buttonDelete.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteItem();				
			}
		});
		panelVerticalButton.add(buttonDelete);
		
		panel.add(panelVerticalButton);

	}
	
	private void buildBottomButtons(FormDisplay formDisplay) {
		Button buttonSave = new Button();
		buttonSave.setText("Save Configuration");
		buttonSave.setStyleName("crux-Button");
		buttonSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				saveConfiguration();				
			}
		});
		formDisplay.addEntry("", buttonSave, HasHorizontalAlignment.ALIGN_DEFAULT);
		
		Button buttonPublish = new Button();
		buttonPublish.setText("Publish Service");
		buttonPublish.setStyleName("crux-Button");
		buttonPublish.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				publishService();				
			}
		});
		formDisplay.addEntry("", buttonPublish, HasHorizontalAlignment.ALIGN_DEFAULT);
	}
	
	private void addItem() {}
	
	private void deleteItem() {}
	
	private void saveConfiguration() {
		api.applicationCreate(serviceName.getValue(), serviceDescription.getValue(), upload.getFilename(), new Callback<String>(){
			@Override
			public void onSuccess(String result) {
				Navigate.to(Navigate.SERVICE_CATALOG_LIST);
			}
			@Override
			public void onError(Exception e) {
			}
		});
	}
	
	private void publishService() {}
}
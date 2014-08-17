package com.mimic.ui.test;


import android.os.Bundle;
import android.text.InputType;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.support.UITextInputItem;

public class TextInputs extends UITableViewActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
        // Text inputs
        UITableViewSection inputsSection = new UITableViewSection(this);
        inputsSection.setHeaderText("Inputs");
        inputsSection.addItem(new UITextInputItem(this, "Name", "Your name"));
        inputsSection.addItem(new UITextInputItem(this, "Password", "Your password", 
        		InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        inputsSection.addItem(new UITextInputItem(this, "Email", "Your email address", 
        		InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS));
        inputsSection.addItem(new UITextInputItem(this, "Phone number", "Your phone number", 
        		InputType.TYPE_CLASS_PHONE));
        inputsSection.addItem(new UITextInputItem(this, "Date", "Date time", 
        		InputType.TYPE_CLASS_DATETIME));
        tableView.addItem(inputsSection);
        
        // Not labeled text inputs
        UITableViewSection noLabelInputsSection = new UITableViewSection(this);
        noLabelInputsSection.setHeaderText("Author");
        noLabelInputsSection.addItem(new UITextInputItem(this, null, "First name"));
        noLabelInputsSection.addItem(new UITextInputItem(this, null, "Last name"));
        noLabelInputsSection.addItem(new UITextInputItem(this, null, "Description about your job"));
        noLabelInputsSection.addItem(new UITextInputItem(this, null, "Multiline text input, you can type very very long text", 
        		InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE));
        tableView.addItem(noLabelInputsSection);
	}	
}

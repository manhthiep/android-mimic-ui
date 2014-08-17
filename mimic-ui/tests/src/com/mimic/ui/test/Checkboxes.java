package com.mimic.ui.test;


import android.os.Bundle;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnCheckBoxAccessoryChangeListener;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;

public class Checkboxes extends UITableViewActivity {
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Checkboxes
		UITableViewSection checkboxesSection = new UITableViewSection(this, "Checkboxes", null);
		
		UITableViewItem checkboxItem1 = new UITableViewItem(this, "Checkbox 1", null, 
				UITableViewItem.STYLE_DEFAULT, UITableViewItem.ACCESSORY_CHECKBOX);
		checkboxItem1.getCheckBoxAccessory().setChecked(true);
		checkboxItem1.setOnCheckBoxAccessoryChangeListener(new OnCheckBoxAccessoryChangeListener() {			
			@Override
			public void onCheckedChanged(UITableViewItem item, boolean isChecked) {
				if (isChecked) {
					makeToast("Checkbox 1 is checked");
				} else {
					makeToast("Checkbox 1 is unchecked");
				}
			}
		});
		checkboxesSection.addItem(checkboxItem1);
		
		UITableViewItem checkboxItem2 = new UITableViewItem(this, "Checkbox 2", null, 
				UITableViewItem.STYLE_DEFAULT, UITableViewItem.ACCESSORY_CHECKBOX);
		checkboxItem2.getCheckBoxAccessory().setClickable(false);
		checkboxItem2.setClickable(true);
		checkboxItem2.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				// get last state
				boolean isChecked = item.getCheckBoxAccessory().isChecked();
				if (isChecked) {
					// set new state
					item.getCheckBoxAccessory().setChecked(false);
					makeToast("Checkbox 2 is unchecked");
				} else {
					// set new state
					item.getCheckBoxAccessory().setChecked(true);
					makeToast("Checkbox 2 is checked");
				}
			}
		});
		checkboxesSection.addItem(checkboxItem2);
		
		tableView.addItem(checkboxesSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Checkboxes.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

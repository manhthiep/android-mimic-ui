package com.mimic.ui.test;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;

public class Accessories extends UITableViewActivity {
	private static final String TAG="Accessories";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Accessories
		UITableViewSection accessoriesSection = new UITableViewSection(this, "Accessories", null);
		
		// No accessory
		UITableViewItem noAccessoryItem = new UITableViewItem(this, "No accessory",
				UITableViewItem.ACCESSORY_NONE);
		accessoriesSection.addItem(noAccessoryItem);
		
		// Disclosure Indicator accessory
		UITableViewItem disclosureIndicator = new UITableViewItem(this, "Disclosure Indicator", 
				UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		disclosureIndicator.setClickable(true);
		disclosureIndicator.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				showInfoDialog("Hey!", "I am a dialog");
			}
		});
		accessoriesSection.addItem(disclosureIndicator);
		
		// Switch accessory
		UITableViewItem switchItem = new UITableViewItem(this, "On Off Switch", 
				UITableViewItem.ACCESSORY_SWITCH);
		switchItem.getSwitchAccessory().setChecked(true);
		switchItem.getSwitchAccessory().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					makeToast("On Off Switch bas been swiched on!");
				} else {
					makeToast("On Off Switch has been swiched off!");
				}
			}
		});
		accessoriesSection.addItem(switchItem);
		
		// Checkbox accessory
		UITableViewItem checkboxItem = new UITableViewItem(this, "Checkbox",
				UITableViewItem.ACCESSORY_CHECKBOX);
		checkboxItem.getCheckBoxAccessory().setChecked(true);
		checkboxItem.getCheckBoxAccessory().setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					makeToast("Checkbox is checked");
				} else {
					makeToast("Checkbox is unchecked");
				}
			}
		});
		accessoriesSection.addItem(checkboxItem);
		
		// Buttons
        UITableViewItem buttonItem = new UITableViewItem(this, "Button",
        		UITableViewItem.ACCESSORY_BUTTON);
        buttonItem.getButtonAccessory().setText("Press me!");
        buttonItem.getButtonAccessory().setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				Log.d(TAG, "Button clicked!");
				makeToast("Button clicked!");
			}
		});
        accessoriesSection.addItem(buttonItem);
        
		tableView.addItem(accessoriesSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Accessories.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	protected void showInfoDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (title != null && !title.isEmpty()) {
			builder.setTitle(title);
		}
		if (message != null && !message.isEmpty()) {
			builder.setMessage(message);
		}
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}
	
	protected void showInfoDialogFromThread(final String title, final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				showInfoDialog(title, message);
			}
		});
	}
}

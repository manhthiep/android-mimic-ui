package com.mimic.ui.test;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnSwitchAccessoryChangeListener;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.support.UISwitchItem;
import com.mimic.ui.tableview.support.UISwitchItem.OnSwitchChangeListener;

public class Switches extends UITableViewActivity {
	private static final String TAG="Switches";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Switches
		UITableViewSection switchesSection = new UITableViewSection(this, "Switches", null);
		UISwitchItem switchItem1 = new UISwitchItem(this, "On Off Switch 1");
		switchItem1.setOnSwitchChangeListener(new OnSwitchChangeListener() {			
			@Override
			public void onCheckedChanged(UISwitchItem switchItem, boolean isChecked) {
				if (isChecked) {
					Log.d(TAG, "On Off Switch 1 switched on!");
					makeToast("On Off Switch 1 switched on!");
				} else {
					Log.d(TAG, "On Off Switch 1 switched off!");
					makeToast("On Off Switch 1 switched off!");
				}				
			}
		});
		switchesSection.addItem(switchItem1);
		
		UISwitchItem switchItem2 = new UISwitchItem(this, "On Off Switch 2", "with subtitle");
		switchItem2.setState(true);
		switchItem2.setOnSwitchChangeListener(new OnSwitchChangeListener() {			
			@Override
			public void onCheckedChanged(UISwitchItem switchItem, boolean isChecked) {
				if (isChecked) {
					Log.d(TAG, "On Off Switch 2 switched on!");
					makeToast("On Off Switch 2 switched on!");
				} else {
					Log.d(TAG, "On Off Switch 2 switched off!");
					makeToast("On Off Switch 2 switched off!");
				}				
			}
		});
		switchesSection.addItem(switchItem2);
		
		UITableViewItem switchItem3 = new UITableViewItem(this, "On Off Switch 3", 
				UITableViewItem.ACCESSORY_SWITCH);
		switchItem3.getSwitchAccessory().setChecked(true);
		switchItem3.setOnSwitchAccessoryChangeListener(new OnSwitchAccessoryChangeListener() {			
			@Override
			public void onCheckedChanged(UITableViewItem item, boolean isChecked) {
				if (isChecked) {
					makeToast("On Off Switch 3 swiched on!");
				} else {
					makeToast("On Off Switch 3 swiched off!");
				}
			}
		});
		switchesSection.addItem(switchItem3);
		tableView.addItem(switchesSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Switches.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

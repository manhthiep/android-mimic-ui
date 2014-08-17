package com.mimic.ui.test;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.support.UISwitchItem;
import com.mimic.ui.tableview.support.UISwitchItem.OnSwitchChangeListener;

public class Sections extends UITableViewActivity {
	private static final String TAG="Sections";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Section with header & footer
		UITableViewSection section1 = new UITableViewSection(this, "Section with header & footer", 
				"This is footer of section 1");
		UITableViewItem section1item1 = new UITableViewItem(this, "Show a dialog", 
				UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		section1item1.setClickable(true);
		section1item1.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				showInfoDialog("Dialog", "Press OK to close.");
			}
		});
		UISwitchItem section1item2 = new UISwitchItem(this, "On Off Switch 1");
		section1item2.setOnSwitchChangeListener(new OnSwitchChangeListener() {			
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
		section1.addItem(section1item2);
		tableView.addItem(section1);

		// Section with header only
		UITableViewSection section2 = new UITableViewSection(this, "Section without footer", null);
		UITableViewItem section2item1 = new UITableViewItem(this, "Section 2 Item 1");
		section2.addItem(section2item1);
		tableView.addItem(section2);
		
		// Section without header & footer
		UITableViewSection section3 = new UITableViewSection(this);
		UITableViewItem section3item1 = new UITableViewItem(this, "Section 3 Item 1");
		UITableViewItem section3item2 = new UITableViewItem(this, "Section 3 Item 2");
		UITableViewItem section3item3 = new UITableViewItem(this, "Section 3 Item 3");
		section3.addItem(section3item1);
		section3.addItem(section3item2);
		section3.addItem(section3item3);
		tableView.addItem(section3);
		
		// Section with footer only
		UITableViewSection section4 = new UITableViewSection(this, null, 
				"This is footer, above section does not contain header");
		UITableViewItem section4item1 = new UITableViewItem(this, "Section 4 Item 1");
		section4.addItem(section4item1);
		tableView.addItem(section4);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Sections.this, message, Toast.LENGTH_SHORT).show();
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

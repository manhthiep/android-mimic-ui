package com.mimic.ui.test;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;

public class Styles extends UITableViewActivity {
	
	private CharSequence[] items = {
			"Excellent",
			"Good",
			"Bad",
			"Ugly"
		};
	private int checkedItem = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
				
		// Default style
		UITableViewSection defaultStyleSection = new UITableViewSection(this, "Default", null);
		UITableViewItem defaultItem1 = new UITableViewItem(this, "Default item");
		UITableViewItem defaultItem2 = new UITableViewItem(this, "Display a dialog", 
				UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		defaultItem2.setClickable(true);
		defaultItem2.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				showInfoDialog("Hey!", "I am a dialog");
			}
		});
		defaultStyleSection.addItem(defaultItem1);
		defaultStyleSection.addItem(defaultItem2);
		tableView.addItem(defaultStyleSection);
		
		// Value style
		UITableViewSection valueStyleSection = new UITableViewSection(this, "Item with value", null);
		UITableViewItem valueItem1 = new UITableViewItem(this, 
				"Value", "abc", 
				UITableViewItem.STYLE_VALUE, 
				UITableViewItem.ACCESSORY_NONE);		
		
		final UITableViewItem valueItem2 = new UITableViewItem(this, 
				"Rate", items[checkedItem].toString(), 
				UITableViewItem.STYLE_VALUE, 
				UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		valueItem2.setClickable(true);
		valueItem2.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {				
				showSingleChoiceDialog("Please select", items, checkedItem, 
						new DialogInterface.OnClickListener() {					
							@Override
							public void onClick(DialogInterface dialog, int which) {
								checkedItem = which;
							}
						}, new DialogInterface.OnClickListener() {							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								valueItem2.setDetailText(items[checkedItem].toString());
							}
						}
				);
			}
		});
		valueStyleSection.addItem(valueItem1);
		valueStyleSection.addItem(valueItem2);
		tableView.addItem(valueStyleSection);
		
		// Subtitle style
		UITableViewSection subtitleStyleSection = new UITableViewSection(this, "Items with subtitle", null);
		UITableViewItem subtitleItem1 = new UITableViewItem(this, "Title", "Subtitle", 
				UITableViewItem.STYLE_SUBTITLE, UITableViewItem.ACCESSORY_NONE);
		subtitleStyleSection.addItem(subtitleItem1);
		
		UITableViewItem subtitleItem2 = new UITableViewItem(this, "Title", "Subtitle and clickable", 
				UITableViewItem.STYLE_SUBTITLE, UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		subtitleItem2.setClickable(true);
		subtitleItem2.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				showInfoDialog("Ooops!", "You've accidentally clicked on an item (with subtitle).\n\nPress OK to continue.");
			}
		});
		subtitleStyleSection.addItem(subtitleItem2);
		tableView.addItem(subtitleStyleSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Styles.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	protected void showSingleChoiceDialog(String title, CharSequence[] items, int checkedItem,
			DialogInterface.OnClickListener choiceListener,
			DialogInterface.OnClickListener positiveListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (title != null && !title.isEmpty()) {
			builder.setTitle(title);
		}
		builder.setSingleChoiceItems(items, checkedItem, choiceListener);
		builder.setPositiveButton("OK", positiveListener);
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.show();
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

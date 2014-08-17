package com.mimic.ui.test;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;

public class UITableViewTestActivity extends UITableViewActivity implements OnItemClickListener {
	
	private CharSequence[] items = {
			"Excellent",
			"Good",
			"Bad",
			"Ugly"
		};
	private int checkedItem = 0;
	
	String[] basicList = {
		"Style",
		"Section",
		"Accessory"
	};
	
	String[] extraList = {
		"Text Input",
		"Slider",
		"Switch",
		"Button",
		"Progress",
		"Checkbox",
		"Single Choice List",
		"Multi Choice List"
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Version
		final UITableViewItem versionItem = new UITableViewItem(this, "Version", "1.0.0", 
				UITableViewItem.STYLE_VALUE, UITableViewItem.ACCESSORY_NONE);
		tableView.addItem(versionItem);
		
		// Rate
		final UITableViewItem rateItem = new UITableViewItem(this, "Rate", items[checkedItem].toString(), 
				UITableViewItem.STYLE_VALUE, UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
		rateItem.setClickable(true);
		rateItem.setOnItemClickListener(new OnItemClickListener() {			
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
								rateItem.setDetailText(items[checkedItem].toString());
							}
						}
				);
			}
		});
		tableView.addItem(rateItem);
		
		// Basics
		UITableViewSection basicSection = new UITableViewSection(this, "BASIC", null);
		for (String basic : basicList) {
				UITableViewItem basicItem = new UITableViewItem(this, basic, 
						UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
				basicItem.setClickable(true);
				basicItem.setOnItemClickListener(this);
				basicSection.addItem(basicItem);
		}
		tableView.addItem(basicSection);
		
		// Extras
		UITableViewSection extraSection = new UITableViewSection(this, "EXTRAS", null);
		for (String extra : extraList) {
				UITableViewItem exItem = new UITableViewItem(this, extra, 
						UITableViewItem.ACCESSORY_DISCLOSURE_INDICATOR);
				exItem.setClickable(true);
				exItem.setOnItemClickListener(this);
				extraSection.addItem(exItem);
		}
		tableView.addItem(extraSection);
	}	

	@Override
	public void onItemClick(UITableViewItem item) {
		if (item.getText().equalsIgnoreCase("Style")) {
			Intent intent = new Intent(this, Styles.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Section")) {
			Intent intent = new Intent(this, Sections.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Accessory")) {
			Intent intent = new Intent(this, Accessories.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Text Input")) {
			Intent intent = new Intent(this, TextInputs.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Slider")) {
			Intent intent = new Intent(this, Sliders.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Switch")) {
			Intent intent = new Intent(this, Switches.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Button")) {
			Intent intent = new Intent(this, Buttons.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Progress")) {
			Intent intent = new Intent(this, Progress.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Checkbox")) {
			Intent intent = new Intent(this, Checkboxes.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Single Choice List")) {
			Intent intent = new Intent(this, SingleChoiceList.class);
			startActivity(intent);
		} else if (item.getText().equalsIgnoreCase("Multi Choice List")) {
			Intent intent = new Intent(this, MultiChoiceList.class);
			startActivity(intent);
		}
	}
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(UITableViewTestActivity.this, message, Toast.LENGTH_SHORT).show();
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
}

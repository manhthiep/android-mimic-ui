package com.mimic.ui.test;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnButtonAccessoryClickListener;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.support.UIButtonItem;
import com.mimic.ui.tableview.support.UIButtonItem.OnButtonClickListener;

public class Buttons extends UITableViewActivity {
	private static final String TAG="Buttons";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();

		// Buttons
		UITableViewSection buttonsSection = new UITableViewSection(this, "Buttons", null);
        UIButtonItem buttonItem1 = new UIButtonItem(this, "Button 1", "Press me!");
        buttonItem1.setOnButtonClickListener(new OnButtonClickListener() {			
			@Override
			public void onClick(UIButtonItem buttonItem) {
				Log.d(TAG, "Button 1 clicked!");
				makeToast("Button 1 clicked!");
			}
		});
        buttonsSection.addItem(buttonItem1);
        
        UIButtonItem buttonItem2 = new UIButtonItem(this, "Button 2", "with subtitle", "Press me, too!");
        buttonItem2.setOnButtonClickListener(new OnButtonClickListener() {			
			@Override
			public void onClick(UIButtonItem buttonItem) {
				Log.d(TAG, "Button 2 clicked!");
				makeToast("Button 2 clicked!");
			}
		});
        buttonsSection.addItem(buttonItem2);
        
        UITableViewItem buttonItem3 = new UITableViewItem(this, "Button 3", "with button accessory", 
        		UITableViewItem.STYLE_SUBTITLE, UITableViewItem.ACCESSORY_BUTTON);
        buttonItem3.getButtonAccessory().setText("Press me, three!");
        buttonItem3.setOnButtonAccessoryClickListener(new OnButtonAccessoryClickListener() {			
			@Override
			public void onClick(UITableViewItem item) {
				Log.d(TAG, "Button 3 clicked!");
				makeToast("Button 3 clicked!");
			}
		});
        buttonsSection.addItem(buttonItem3);
        tableView.addItem(buttonsSection);
		
		// Styled items
		UITableViewSection styledItemsSection = new UITableViewSection(this, "Styled Items", null);
		final UITableViewItem styledItem1 = new UITableViewItem(this, "Press me!");
		styledItem1.getTextView().setGravity(Gravity.LEFT);
		styledItem1.getTextView().setTextColor(Color.BLUE);
		styledItem1.setClickable(true);
		styledItem1.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				Log.d(TAG, "Styled Item 1 clicked!");
				makeToast("Styled Item 1 clicked!");
			}
		});
		styledItemsSection.addItem(styledItem1);
		UITableViewItem styledItem2 = new UITableViewItem(this, "Press me!");
		styledItem2.getTextView().setGravity(Gravity.CENTER);
		styledItem2.getTextView().setTextColor(Color.RED);
		styledItem2.setClickable(true);
		styledItem2.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				Log.d(TAG, "Styled Item 2 clicked!");
				makeToast("Styled Item 2 clicked!");
			}
		});
		styledItemsSection.addItem(styledItem2);
		tableView.addItem(styledItemsSection);		
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Buttons.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

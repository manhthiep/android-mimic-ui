package com.mimic.ui.test;


import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.support.UISingleChoiceListSection;
import com.mimic.ui.tableview.support.UISingleChoiceListSection.OnSelectedItemChangeListener;

public class SingleChoiceList extends UITableViewActivity {
	
	private String[] choiceItems = {
			"Item 1",
			"Item 2",
			"Item 3"
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		ArrayList<String> choiceItemList = new ArrayList<String>(Arrays.asList(choiceItems));
		
		// Single choice list
		UISingleChoiceListSection singleChoiceSection = 
				new UISingleChoiceListSection(this, choiceItemList, 0, 
						"Select a item", null);
		singleChoiceSection.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
			@Override
			public void onSelectedItemChanged(int selectedItemIndex) {
				if (selectedItemIndex < choiceItems.length) {
					makeToast("Selected item: " + choiceItems[selectedItemIndex]);
				}
			}
		});
        
		tableView.addItem(singleChoiceSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(SingleChoiceList.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

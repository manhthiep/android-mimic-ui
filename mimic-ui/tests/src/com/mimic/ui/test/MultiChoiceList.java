package com.mimic.ui.test;


import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.support.UIMultiChoiceListSection;
import com.mimic.ui.tableview.support.UIMultiChoiceListSection.OnMultiChoiceItemStateChangeListener;

public class MultiChoiceList extends UITableViewActivity {
	
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
		
		// Multi choice list
		UIMultiChoiceListSection multiChoiceSection = 
				new UIMultiChoiceListSection(this, choiceItemList, new int[]{1, 3}, 
						"Select item(s)", null);
		multiChoiceSection.setOnMultiChoiceItemStateChangeListener(
				new OnMultiChoiceItemStateChangeListener() {			
			@Override
			public void onItemCheckedStateChanged(int itemIndex, boolean isChecked) {
				if (itemIndex < choiceItems.length) {
					if (isChecked) {
						makeToast("Item: '" + choiceItems[itemIndex] + "' is checked.");
					} else {
						makeToast("Item: '" + choiceItems[itemIndex] + "' is unchecked.");
					}					
				}
			}
		});
        
		tableView.addItem(multiChoiceSection);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MultiChoiceList.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

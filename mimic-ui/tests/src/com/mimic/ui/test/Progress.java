package com.mimic.ui.test;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;
import com.mimic.ui.tableview.support.UIProgressItem;

public class Progress extends UITableViewActivity {
	private static final String TAG="Progress";
	
	private boolean progressShown = true;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Progress item
		UIProgressItem progressItem = new UIProgressItem(this, "Processing...");
		tableView.addItem(progressItem);

		// Progress accessory
		final UITableViewItem progressAccessoryItem = new UITableViewItem(this, 
				"Press me!", UITableViewItem.ACCESSORY_PROGRESS);
		progressAccessoryItem.getTextView().setGravity(Gravity.LEFT);
		progressAccessoryItem.getTextView().setTextColor(Color.BLUE);
		progressAccessoryItem.setClickable(true);
		progressAccessoryItem.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(UITableViewItem item) {
				Log.d(TAG, "Progress Accessory Item clicked!");
				makeToast("Progress Accessory Item clicked!");
				if (progressShown) {
					progressShown = false;
					progressAccessoryItem.hideAccessory();
				} else {
					progressShown = true;
					progressAccessoryItem.showAccessory();
				}
			}
		});
		tableView.addItem(progressAccessoryItem);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Progress.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

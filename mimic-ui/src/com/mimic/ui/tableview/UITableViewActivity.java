package com.mimic.ui.tableview;


import android.app.Activity;
import android.os.Bundle;

import com.mimic.ui.R;

public abstract class UITableViewActivity extends Activity {
	
	private UITableView mTableView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.uitableview_activity);
	     mTableView = (UITableView) findViewById(R.id.tableView);
	     populateViews();
	     mTableView.commit();
	}
	
	protected UITableView getUITableView() {
		return mTableView;
	}
	
	protected abstract void populateViews();
}

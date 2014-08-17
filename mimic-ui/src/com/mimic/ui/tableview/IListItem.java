package com.mimic.ui.tableview;

import android.view.View;

public interface IListItem {

	public boolean isClickable();
	
	public void setClickable(boolean clickable);
	
	public View getItemView();
	
}

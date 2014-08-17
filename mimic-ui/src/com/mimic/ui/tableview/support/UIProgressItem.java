package com.mimic.ui.tableview.support;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UIProgressItem extends UITableViewItem {
	
	private ProgressBar mProgressBar = null;
	private TextView mMessage = null;

	public UIProgressItem(Context context, String message) {
		super(context, message);
		setStyle(STYLE_NONE);
		setupProgressViews();
		setMessage(message);
	}
		
	void setupProgressViews() {
		LinearLayout progressItemContentView = (LinearLayout) getInflater().inflate(
				R.layout.uiprogresstem, null);
		mProgressBar = (ProgressBar) progressItemContentView.findViewById(R.id.progress);
		mMessage = (TextView) progressItemContentView.findViewById(R.id.message);
		getItemView().setPadding(0, 0, 0, 0);
		getItemView().setMinimumHeight(0);
		getContentView().removeAllViews();
		getContentView().setPadding(0, 0, 0, 0);
		getContentView().addView(progressItemContentView);
	}

	public ProgressBar getProgressView() {
		return mProgressBar;
	}
		
	public TextView getMessageView() {
		return mMessage;
	}	
	
	public void setMessage(String message) {
		if (message != null) {
			mMessage.setText(message);
			mMessage.setVisibility(View.VISIBLE);
		} else {
			mMessage.setVisibility(View.GONE);
		}
	}
	
	public void hide() {
		getItemView().setVisibility(View.GONE);
	}
	
	public void show() {
		getItemView().setVisibility(View.VISIBLE);
	}

	@Override
	public boolean isClickable() {
		return false;
	}

	@Override
	public void setClickable(boolean clickable) {
		// do nothing
	}
}

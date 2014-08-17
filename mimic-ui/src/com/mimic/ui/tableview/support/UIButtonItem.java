package com.mimic.ui.tableview.support;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UIButtonItem extends UITableViewItem {
	
	private Button mButtonView = null;
	
	public UIButtonItem(Context context, String label, String buttonLabel) {
		super(context, label); // style : STYLE_DEFAULT
		setupButtonViews(context);
		setLabel(label);
		setSubtitle(null);
		setButtonLabel(buttonLabel);
	}
	
	public UIButtonItem(Context context, String label, String subtitle, String buttonLabel) {
		super(context, label, subtitle, STYLE_SUBTITLE, ACCESSORY_NONE);
		setupButtonViews(context);
		setLabel(label);
		setSubtitle(subtitle);
		setButtonLabel(buttonLabel);
	}
	
	void setupButtonViews(Context context) {
		mButtonView = (Button) getInflater().inflate(R.layout.accessory_button, null);
		mButtonView.setClickable(true);
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, 
				context.getResources().getDisplayMetrics());
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, height);
		getAccessoryView().removeAllViews();
		getAccessoryView().addView(mButtonView, params);
	}
	
	public TextView getLabelView() {
		return getTextView();
	}
	
	public void setLabel(String label) {
		if (label != null) {
			setText(label);
			getTextView().setVisibility(View.VISIBLE);
		} else {
			getTextView().setVisibility(View.GONE);
		}
	}

	public void setLabelColor(int color) {
		if (color > -1) {
			getTextView().setTextColor(color);
		}
	}
	
	public void showLabel() {
		getTextView().setVisibility(View.VISIBLE);
	}
	
	public void hideLabel() {
		getTextView().setVisibility(View.GONE);
	}
	
	public TextView getSubtitleView() {
		return getDetailTextView();
	}
	
	public void setSubtitle(String subtitle) {
		if (getDetailTextView() != null) {
			if (subtitle != null) {
				setDetailText(subtitle);
				getDetailTextView().setVisibility(View.VISIBLE);
			} else {
				getDetailTextView().setVisibility(View.GONE);
			}
		}
	}

	public Button getButtonView() {
		return mButtonView;
	}
	
	public void setButtonLabel(String label) {
		mButtonView.setText(label);
	}
	
	public interface OnButtonClickListener {
		public void onClick(UIButtonItem buttonItem);
	}
	
	public void setOnButtonClickListener(final OnButtonClickListener listener) {
		mButtonView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				listener.onClick(UIButtonItem.this);
			}
		});
	}
	
	@Override
	public boolean isClickable() {
		return true;
	}
}

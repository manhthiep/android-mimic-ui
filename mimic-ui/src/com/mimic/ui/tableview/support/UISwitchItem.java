package com.mimic.ui.tableview.support;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UISwitchItem extends UITableViewItem {
	
	private Switch mSwitchView = null;
	
	public UISwitchItem(Context context, String label) {
		super(context, label); // style : STYLE_DEFAULT
		setupSwitchViews();
		setLabel(label);
		setSubtitle(null);
		setClickable(false);
	}
	
	public UISwitchItem(Context context, String label, String subtitle) {
		super(context, label, subtitle, STYLE_SUBTITLE, ACCESSORY_NONE);
		setupSwitchViews();
		setLabel(label);
		setClickable(false);
	}
	
	void setupSwitchViews() {
		mSwitchView = (Switch) getInflater().inflate(
				R.layout.accessory_switch_button, null);
		getAccessoryView().removeAllViews();
		getAccessoryView().addView(mSwitchView);
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

	public Switch getSwitchView() {
		return mSwitchView;
	}
	
	public boolean getState() {
		return mSwitchView.isChecked();
	}
	
	public void setState(boolean state) {
		mSwitchView.setChecked(true);
	}
	
	public interface OnSwitchChangeListener {
		public void onCheckedChanged(UISwitchItem switchItem, boolean isChecked);
	}
	
	public void setOnSwitchChangeListener(final OnSwitchChangeListener listener) {
		mSwitchView.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				listener.onCheckedChanged(UISwitchItem.this, isChecked);
			}
		});
	}
	
	@Override
	public boolean isClickable() {
		return false;
	}
}

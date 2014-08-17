package com.mimic.ui.tableview.support;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UISliderItem extends UITableViewItem {
	
	private TextView mLabel = null;
	private SeekBar mSliderView = null;
	private TextView mValue = null;
	
	public UISliderItem(Context context, String label) {
		super(context, label);
		setStyle(STYLE_NONE);
		setupSliderViews();
		setLabel(label);
	}
	
	void setupSliderViews() {
		LinearLayout sliderItemContentView = (LinearLayout) getInflater().inflate(
				R.layout.uislideritem, null);
		mLabel = (TextView) sliderItemContentView.findViewById(R.id.label);
		mSliderView = (SeekBar) sliderItemContentView.findViewById(R.id.slider);
		mValue = (TextView) sliderItemContentView.findViewById(R.id.value);
		mSliderView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mValue.setText(String.valueOf(progress));
			}
		});
		getContentView().removeAllViews();
		getContentView().addView(sliderItemContentView);
	}
	
	public interface OnSliderChangeListener {
		public void onValueChanged(UISliderItem sliderItem, int value, boolean fromUser);
	}
	
	public void setOnSliderChangeListener(final OnSliderChangeListener listener) {
		mSliderView.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mValue.setText(String.valueOf(progress));
				listener.onValueChanged(UISliderItem.this, progress, fromUser);
			}
		});
	}
	
	public TextView getLabelView() {
		return mLabel;
	}
	
	public void setLabel(String label) {
		if (label != null) {
			mLabel.setText(label);
			mLabel.setVisibility(View.VISIBLE);
		} else {
			mLabel.setVisibility(View.GONE);
		}
	}

	public void setLabelColor(int color) {
		if (color > -1) {
			mLabel.setTextColor(color);
		}
	}
	
	public void showItemLabel() {
		mLabel.setVisibility(View.VISIBLE);
	}
	
	public void hideItemLabel() {
		mLabel.setVisibility(View.GONE);
	}

	public ProgressBar getSliderView() {
		return mSliderView;
	}
	
	public int getValue() {
		return mSliderView.getProgress();
	}
	
	public void setValue(int value) {
		mSliderView.setProgress(value);
	}
	
	public TextView getValueLabelView() {
		return mValue;
	}
	
	public void showValueLabel() {
		mValue.setVisibility(View.VISIBLE);
	}
	
	public void hideValueLabel() {
		mValue.setVisibility(View.GONE);
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

package com.mimic.ui.tableview.support;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UIVolumeControlItem extends UITableViewItem {
	
	private SeekBar mSliderView = null;
	
	public UIVolumeControlItem(Context context, String label) {
		super(context, label);
		setStyle(STYLE_NONE);
		setupSliderViews();
	}
	
	void setupSliderViews() {
		LinearLayout volumeControlItemContentView = (LinearLayout) getInflater().inflate(
				R.layout.uivolumecontrolitem, null);
		mSliderView = (SeekBar) volumeControlItemContentView.findViewById(R.id.slider);
		getContentView().removeAllViews();
		getContentView().addView(volumeControlItemContentView);
	}
	
	public interface OnVolumeChangeListener {
		public void onVolumeChanged(UIVolumeControlItem sliderItem, int value, boolean fromUser);
	}
	
	public void setOnVolumeChangeListener(final OnVolumeChangeListener listener) {
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
				listener.onVolumeChanged(UIVolumeControlItem.this, progress, fromUser);
			}
		});
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
	
	@Override
	public boolean isClickable() {
		return false;
	}

	@Override
	public void setClickable(boolean clickable) {
		// do nothing
	}
}

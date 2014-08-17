package com.mimic.ui.test;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mimic.ui.tableview.UITableView;
import com.mimic.ui.tableview.UITableViewActivity;
import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.support.UISliderItem;
import com.mimic.ui.tableview.support.UISliderItem.OnSliderChangeListener;
import com.mimic.ui.tableview.support.UIVolumeControlItem;

public class Sliders extends UITableViewActivity {
	private static final String TAG="Sliders";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void populateViews() {
		UITableView tableView = getUITableView();
		
		// Slider
        UITableViewSection sliderSection = new UITableViewSection(this);
        final UITableViewItem sliderStatusItem = new UITableViewItem(this, 
        		"Slider status", "0", 
        		UITableViewItem.STYLE_VALUE, UITableViewItem.ACCESSORY_NONE);   
        sliderSection.addItem(sliderStatusItem);
        UISliderItem sliderItem = new UISliderItem(this, "Slider");
        sliderItem.hideItemLabel();
        sliderItem.hideValueLabel();
        sliderItem.setOnSliderChangeListener(new OnSliderChangeListener() {			
			@Override
			public void onValueChanged(UISliderItem sliderItem, int value, boolean fromUser) {
				Log.d(TAG, "Slider value changed:" + String.valueOf(value));
				sliderStatusItem.setDetailText(String.valueOf(value));
			}
		});
        sliderItem.setValue(45);
        sliderSection.addItem(sliderItem);
        tableView.addItem(sliderSection);
        
        // Volume Control
        UIVolumeControlItem volumeControlItem = new UIVolumeControlItem(this, null);
        volumeControlItem.setValue(75);
        tableView.addItem(volumeControlItem);
	}	
	
	protected void makeToast(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(Sliders.this, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	protected void showSingleChoiceDialog(String title, CharSequence[] items, int checkedItem,
			DialogInterface.OnClickListener choiceListener,
			DialogInterface.OnClickListener positiveListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (title != null && !title.isEmpty()) {
			builder.setTitle(title);
		}
		builder.setSingleChoiceItems(items, checkedItem, choiceListener);
		builder.setPositiveButton("OK", positiveListener);
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.show();
	}
	
	protected void showInfoDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (title != null && !title.isEmpty()) {
			builder.setTitle(title);
		}
		if (message != null && !message.isEmpty()) {
			builder.setMessage(message);
		}
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}
	
	protected void showInfoDialogFromThread(final String title, final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				showInfoDialog(title, message);
			}
		});
	}
}

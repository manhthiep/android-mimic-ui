package com.mimic.ui.tableview.support;


import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;

public class UISingleChoiceListSection extends UITableViewSection implements OnItemClickListener {
	
	private ArrayList<UITableViewItem> mChoiceItemViews;
	private ArrayList<String> mChoiceItems;
	private int mCurrentCheckedItemIndex = 0;
	
	private OnSelectedItemChangeListener mListener;

	public UISingleChoiceListSection(Context context, ArrayList<String> items, int checkedItem) {
		super(context);
		mChoiceItemViews = new ArrayList<UITableViewItem>();
		mChoiceItems = new ArrayList<String>(items);
		mCurrentCheckedItemIndex = checkedItem;
		setupSingleChoiceListViews(context);
	}
	
	public UISingleChoiceListSection(Context context, ArrayList<String> items, 
			int checkedItem, String headerText, String footerText) {
		super(context, headerText, footerText);
		mChoiceItemViews = new ArrayList<UITableViewItem>();
		mChoiceItems = new ArrayList<String>(items);
		mCurrentCheckedItemIndex = checkedItem;
		setupSingleChoiceListViews(context);
	}
	
	void setupSingleChoiceListViews(Context context) {
		int count = 0;
		for (String item : mChoiceItems) {
			UITableViewItem listItem = new UITableViewItem(context, item,
					UITableViewItem.ACCESSORY_RADIO);
			Log.d("UISingleChoiceListSection", "padding: top=" 
					+ String.valueOf(listItem.getRadioAccessory().getPaddingTop()) 
					+ ", bottom=" + String.valueOf(listItem.getRadioAccessory().getPaddingBottom()));
			listItem.getRadioAccessory().setClickable(false);
			if (count == mCurrentCheckedItemIndex) {
				listItem.getRadioAccessory().setChecked(true);
			} else {
				listItem.getRadioAccessory().setChecked(false);
			}
			listItem.setClickable(true);
			listItem.setOnItemClickListener(this);
			mChoiceItemViews.add(listItem);
			addItem(listItem);
			count++;
		}
	}
	
	public void setCheckedItem(int itemIndex) {
		if (itemIndex >= mChoiceItemViews.size())
			return;
		if (itemIndex == mCurrentCheckedItemIndex)
			return;
		UITableViewItem item = mChoiceItemViews.get(itemIndex);
		UITableViewItem lastCheckedItem = mChoiceItemViews.get(mCurrentCheckedItemIndex);
		lastCheckedItem.getRadioAccessory().setChecked(true);
		item.getRadioAccessory().setChecked(true);
		mCurrentCheckedItemIndex = itemIndex;
	}
	
	public int getCheckedItem() {
		return mCurrentCheckedItemIndex;
	}

	@Override
	public void onItemClick(UITableViewItem item) {
		int itemIndex = mChoiceItemViews.indexOf(item);
		if (itemIndex != -1) {
			UITableViewItem lastCheckedItem = mChoiceItemViews.get(mCurrentCheckedItemIndex);
			lastCheckedItem.getRadioAccessory().setChecked(false);
			item.getRadioAccessory().setChecked(true);
			mCurrentCheckedItemIndex = itemIndex;
			if (mListener != null) {
				mListener.onSelectedItemChanged(itemIndex);
			}
		}
	}
	
	public interface OnSelectedItemChangeListener {
		void onSelectedItemChanged(int selectedItemIndex);
	}
	
	public void setOnSelectedItemChangeListener(OnSelectedItemChangeListener listener) {
		this.mListener = listener;
	}
}

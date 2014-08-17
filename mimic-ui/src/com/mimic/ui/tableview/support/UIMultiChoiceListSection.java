package com.mimic.ui.tableview.support;


import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mimic.ui.tableview.UITableViewItem;
import com.mimic.ui.tableview.UITableViewSection;
import com.mimic.ui.tableview.UITableViewItem.OnItemClickListener;

public class UIMultiChoiceListSection extends UITableViewSection implements OnItemClickListener {
	
	private ArrayList<UITableViewItem> mChoiceItemViews;
	private ArrayList<String> mChoiceItems;
	
	private OnMultiChoiceItemStateChangeListener mListener;

	public UIMultiChoiceListSection(Context context, ArrayList<String> items, 
			int[] checkedItems) {
		super(context);
		mChoiceItemViews = new ArrayList<UITableViewItem>();
		mChoiceItems = new ArrayList<String>(items);
		setupMultiChoiceListViews(context);
		setCheckedItems(checkedItems);
	}
	
	public UIMultiChoiceListSection(Context context, ArrayList<String> items, 
			int[] checkedItems, String headerText, String footerText) {
		super(context, headerText, footerText);
		mChoiceItemViews = new ArrayList<UITableViewItem>();
		mChoiceItems = new ArrayList<String>(items);
		setupMultiChoiceListViews(context);
		setCheckedItems(checkedItems);
	}
	
	void setupMultiChoiceListViews(Context context) {
		for (String item : mChoiceItems) {
			UITableViewItem listItem = new UITableViewItem(context, item,
					UITableViewItem.ACCESSORY_CHECKBOX);
			Log.d("UIMultiChoiceListSection", "padding: top=" 
					+ String.valueOf(listItem.getCheckBoxAccessory().getPaddingTop()) 
					+ ", bottom=" + String.valueOf(listItem.getCheckBoxAccessory().getPaddingBottom()));
			listItem.getCheckBoxAccessory().setClickable(false);
			listItem.getCheckBoxAccessory().setChecked(false);
			listItem.setClickable(true);
			listItem.setOnItemClickListener(this);
			mChoiceItemViews.add(listItem);
			addItem(listItem);
		}
	}
	
	public void setCheckedItem(int itemIndex) {
		if (itemIndex >= mChoiceItemViews.size())
			return;
		UITableViewItem item = mChoiceItemViews.get(itemIndex);
		item.getCheckBoxAccessory().setChecked(true);
	}

	public boolean isItemChecked(int itemIndex) {
		if (itemIndex >= mChoiceItemViews.size())
			return false;
		UITableViewItem item = mChoiceItemViews.get(itemIndex);
		return item.getCheckBoxAccessory().isChecked();
	}
	
	public void setCheckedItems(int[] itemIndexes) {
		if (itemIndexes.length > mChoiceItemViews.size())
			return;
		for (int i = 0; i < itemIndexes.length; i++) {
			int itemIndex = itemIndexes[i];
			if (itemIndex >= mChoiceItemViews.size())
				continue;
			UITableViewItem item = mChoiceItemViews.get(itemIndex);
			item.getCheckBoxAccessory().setChecked(true);
		}
	}
	
	public int[] getCheckedItems() {
		ArrayList<Integer> checkedItemList = new ArrayList<Integer>();
		int index = 0;
		for (UITableViewItem item : mChoiceItemViews) {
			if (item.getCheckBoxAccessory().isChecked()) {
				checkedItemList.add(Integer.valueOf(index));
			}
			index++;
		}
		if (checkedItemList.size() == 0) {
			return new int[0];
		}
		int checkedItems[] = new int[checkedItemList.size()];
		index = 0;
		for (Integer checkIndex : checkedItemList) {
			checkedItems[index] = checkIndex.intValue();
			index++;
		}
		return checkedItems;
	}
	
	@Override
	public void onItemClick(UITableViewItem item) {
		int itemIndex = mChoiceItemViews.indexOf(item);
		if (itemIndex != -1) {
			boolean isChecked = item.getCheckBoxAccessory().isChecked();
			if (isChecked) {
				item.getCheckBoxAccessory().setChecked(false);
				isChecked = false;
			} else {
				item.getCheckBoxAccessory().setChecked(true);
				isChecked = true;
			}
			if (mListener != null) {
				mListener.onItemCheckedStateChanged(itemIndex, isChecked);
			}
		}
	}
	
	public interface OnMultiChoiceItemStateChangeListener {
		void onItemCheckedStateChanged(int itemIndex, boolean isChecked);
	}
	
	public void setOnMultiChoiceItemStateChangeListener(
			OnMultiChoiceItemStateChangeListener listener) {
		this.mListener = listener;
	}
}

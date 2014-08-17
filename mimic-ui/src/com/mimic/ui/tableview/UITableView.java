package com.mimic.ui.tableview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mimic.ui.R;

public class UITableView extends LinearLayout {

	private LayoutInflater mInflater;
	private LinearLayout mMainContainer;
	private LinearLayout mItemsContainer;
	
	private List<IListItem> mItemList;

	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mItemList = new ArrayList<IListItem>();
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMainContainer = (LinearLayout) mInflater.inflate(R.layout.uitableview, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		addView(mMainContainer, params);
		mItemsContainer = (LinearLayout) mMainContainer.findViewById(R.id.itemsContainer);
	}

	public void addItem(IListItem item) {
		mItemList.add(item);
	}

	public void commit() {
		for (IListItem item : mItemList) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			params.setMargins(0, 20, 0, 30);
			mItemsContainer.addView(item.getItemView(), params);
		}
	}

	public int getItemCount() {
		return mItemList.size();
	}

	public void clear() {
		mItemList.clear();
		mItemsContainer.removeAllViews();
	}
}

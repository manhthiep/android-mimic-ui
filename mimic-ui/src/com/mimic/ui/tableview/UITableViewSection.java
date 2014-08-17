package com.mimic.ui.tableview;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimic.ui.R;

public class UITableViewSection implements IListItem {
	
	private List<IListItem> mItemList;
	
	private LayoutInflater mInflater;
	private ViewGroup mHeaderView;
	private ViewGroup mFooterView;
	private TextView mHeaderTextView;
	private TextView mFooterTextView;
	
	private LinearLayout mSectionViewsContainer;
	private LinearLayout mSectionItemsContainer;

	public UITableViewSection(Context context) {
		mItemList = new ArrayList<IListItem>();
		setupSectionViews(context);
		setHeaderText(null);
		setFooterText(null);
	}
	
	public UITableViewSection(Context context, String headerText, String footerText) {
		mItemList = new ArrayList<IListItem>();
		setupSectionViews(context);
		setHeaderText(headerText);
		setFooterText(footerText);
	}
	
	void setupSectionViews(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mSectionViewsContainer = (LinearLayout) mInflater.inflate(R.layout.uitableviewsection, null);
		mSectionItemsContainer = (LinearLayout) mSectionViewsContainer.findViewById(R.id.sectionItemsContainer);
		mHeaderView = (ViewGroup) mSectionViewsContainer.findViewById(R.id.sectionHeaderView);
		mHeaderTextView = (TextView) mSectionViewsContainer.findViewById(R.id.sectionHeaderText);
		mFooterView = (ViewGroup) mSectionViewsContainer.findViewById(R.id.sectionFooterView);
		mFooterTextView = (TextView) mSectionViewsContainer.findViewById(R.id.sectionFooterText);
	}
		
	public int getItemCount() {
		return mItemList.size();
	}
	
	public void clear() {
		mItemList.clear();
		mSectionItemsContainer.removeAllViews();
	}
	
	public void addItem(IListItem item) {
		mItemList.add(item);
		View itemView = item.getItemView();
		itemView.setClickable(item.isClickable());
		mSectionItemsContainer.addView(itemView);
	}
	
	public ViewGroup getHeaderView() {
		return mHeaderView;
	}
	
	public TextView getHeaderTextView() {
		return mHeaderTextView;
	}

	public void setHeaderText(String text) {
		if (text != null) {
			mHeaderTextView.setVisibility(View.VISIBLE);
			mHeaderTextView.setText(text);
		} else {
			mHeaderTextView.setVisibility(View.GONE);
		}
	}
	
	public ViewGroup getFooterView() {
		return mFooterView;
	}
	
	public TextView getFooterTextView() {
		return mFooterTextView;
	}

	public void setFooterText(String text) {
		if (text != null) {
			mFooterTextView.setVisibility(View.VISIBLE);
			mFooterTextView.setText(text);
		} else {
			mFooterTextView.setVisibility(View.GONE);
		}
	}
	
	@Override
	public View getItemView() {
		return mSectionViewsContainer;
	}

	@Override
	public boolean isClickable() {
		return false;
	}

	@Override
	public void setClickable(boolean clickable) {
	}
}

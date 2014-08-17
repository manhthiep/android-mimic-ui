package com.mimic.ui.tableview;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.mimic.ui.R;

public class UITableViewItem implements IListItem {

	private int mAccessoryType = ACCESSORY_NONE;
	private int mStyle = STYLE_DEFAULT;
	
	private LayoutInflater mInflater;
	private LinearLayout mItemContainer;
	private ImageView mImageView;
	private ViewGroup mContentView;
	private ViewGroup mAccessoryView;
	private TextView mTextView;
	private TextView mDetailTextView;
	private ImageView mDisclosureIndicator;
	private ProgressBar mProgressAccessory;
	private Button mButtonAccessory;
	private Switch mSwitchAccessory;
	private CheckBox mCheckBoxAccessory;
	private RadioButton mRadioAccessory;
	
	private Object mPrivateData;
	private int mButtonAccessoryHeight;
	private int mCheckBoxAccessoryHeight;
	private int mRadioAccessoryHeight;
	
	public UITableViewItem(Context context, String text) {
		this.mStyle = STYLE_DEFAULT;
		setupItemViews(context);
		setText(text);
		setDetailText(null);
		setAccessoryType(ACCESSORY_NONE);
		setClickable(false);
	}

	public UITableViewItem(Context context, String text, int accessoryType) {
		this.mStyle = STYLE_DEFAULT;
		setupItemViews(context);
		setText(text);
		setDetailText(null);
		setAccessoryType(accessoryType);
		setClickable(false);
	}
	
	public UITableViewItem(Context context, String text, String detailText, int style, int accessoryType) {
		this.mStyle = style;
		setupItemViews(context);
		setText(text);
		setDetailText(detailText);
		setAccessoryType(accessoryType);
		setClickable(false);
	}

	public View getItemView() {
		return mItemContainer;
	}
	
	void setupItemViews(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		mButtonAccessoryHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, metrics);
		mCheckBoxAccessoryHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, metrics);
		mRadioAccessoryHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, metrics);
		/* item views */
		mItemContainer = (LinearLayout) mInflater.inflate(R.layout.uitableviewitem, null);
		mImageView = (ImageView) mItemContainer.findViewById(R.id.imageView);
		mContentView = (ViewGroup) mItemContainer.findViewById(R.id.contentView);
		mAccessoryView = (ViewGroup) mItemContainer.findViewById(R.id.accessoryView);
		setupStyleViews();
	}

	public static final int STYLE_NONE = 0;
	public static final int STYLE_DEFAULT = 1;
	public static final int STYLE_SUBTITLE = 2;
	public static final int STYLE_VALUE = 3;
	
	public void setStyle(int style) {
		this.mStyle = style;
		setupStyleViews();
	}
	
	void setupStyleViews() {
		if (this.mStyle == STYLE_DEFAULT) {
			LinearLayout defaultContentView = (LinearLayout) mInflater.inflate(R.layout.uitableviewitem_default, null);
			mTextView = (TextView) defaultContentView.findViewById(R.id.textView);
			mContentView.removeAllViews();
			mContentView.addView(defaultContentView);
		} else if (this.mStyle == STYLE_SUBTITLE) {
			LinearLayout subtitleContentView = (LinearLayout) mInflater.inflate(R.layout.uitableviewitem_subtitle, null);
			mTextView = (TextView) subtitleContentView.findViewById(R.id.textView);
			mDetailTextView = (TextView) subtitleContentView.findViewById(R.id.subtitleView);
			mContentView.removeAllViews();
			mContentView.addView(subtitleContentView);
		} else if (this.mStyle == STYLE_VALUE) {
			LinearLayout valueContentView = (LinearLayout) mInflater.inflate(R.layout.uitableviewitem_value, null);
			mTextView = (TextView) valueContentView.findViewById(R.id.textView);
			mDetailTextView = (TextView) valueContentView.findViewById(R.id.valueTextView);
			mContentView.removeAllViews();
			mContentView.addView(valueContentView);
		} else if (this.mStyle == STYLE_NONE) {
			mContentView.removeAllViews();
		}
	}
	
	public void setPrivateData(Object data) {
		mPrivateData = data;
	}
	
	public Object getPrivateData() {
		return mPrivateData;
	}

	public LayoutInflater getInflater() {
		return mInflater;
	}
	
	public ImageView getImageView() {
		return this.mImageView;
	}
	
	public ViewGroup getContentView() {
		return this.mContentView;
	}
	
	public TextView getTextView() {
		return this.mTextView;
	}
	
	public String getText() {
		if (this.mTextView != null) {
			return this.mTextView.getText().toString();
		}
		return null;
	}

	public void setText(String text) {
		if (this.mTextView != null) {
			if(text != null) {
				this.mTextView.setText(text);
				this.mTextView.setVisibility(View.VISIBLE);
			} else {
				this.mTextView.setVisibility(View.GONE);
			}
		}
	}

	public TextView getDetailTextView() {
		return this.mDetailTextView;
	}
	
	public String getDetailText() {
		if (this.mDetailTextView != null) {
			return this.mDetailTextView.getText().toString();
		}
		return null;
	}

	public void setDetailText(String text) {
		if (this.mDetailTextView != null) {
			if(text != null) {
				this.mDetailTextView.setText(text);
				this.mDetailTextView.setVisibility(View.VISIBLE);
			} else {
				this.mDetailTextView.setVisibility(View.GONE);
			}
		}		
	}

	public static final int ACCESSORY_NONE = 0;
	public static final int ACCESSORY_DISCLOSURE_INDICATOR = 1;
	public static final int ACCESSORY_PROGRESS = 2;
	public static final int ACCESSORY_BUTTON = 3;
	public static final int ACCESSORY_SWITCH = 4;
	public static final int ACCESSORY_CHECKBOX = 5;
	public static final int ACCESSORY_RADIO = 6;

	public ViewGroup getAccessoryView() {
		return this.mAccessoryView;
	}
	
	public ProgressBar getProgressAccessory() {
		return mProgressAccessory;
	}
	
	public Button getButtonAccessory() {
		return mButtonAccessory;
	}
	
	public Switch getSwitchAccessory() {
		return mSwitchAccessory;
	}

	public CheckBox getCheckBoxAccessory() {
		return mCheckBoxAccessory;
	}
	
	public RadioButton getRadioAccessory() {
		return mRadioAccessory;
	}
	
	public void setAccessoryType(int type) {
		this.mAccessoryType = type;
		if (mAccessoryType == ACCESSORY_DISCLOSURE_INDICATOR) {
			mAccessoryView.removeAllViews();
			mDisclosureIndicator = (ImageView) mInflater.inflate(R.layout.accessory_disclosure_indicator, null);
			mAccessoryView.addView(mDisclosureIndicator);
		} else if (mAccessoryType == ACCESSORY_PROGRESS) {
			mAccessoryView.removeAllViews();
			mProgressAccessory = (ProgressBar) mInflater.inflate(R.layout.accessory_progress, null);
			mAccessoryView.addView(mProgressAccessory);
		} else if (mAccessoryType == ACCESSORY_BUTTON) {
			mAccessoryView.removeAllViews();
			mButtonAccessory = (Button) mInflater.inflate(R.layout.accessory_button, null);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, mButtonAccessoryHeight);
			mAccessoryView.addView(mButtonAccessory, params);
		} else if (mAccessoryType == ACCESSORY_SWITCH) {
			mAccessoryView.removeAllViews();
			mSwitchAccessory = (Switch) mInflater.inflate(R.layout.accessory_switch_button, null);
			mAccessoryView.addView(mSwitchAccessory);
		} else if (mAccessoryType == ACCESSORY_CHECKBOX) {
			mAccessoryView.removeAllViews();
			mCheckBoxAccessory = (CheckBox) mInflater.inflate(R.layout.accessory_checkbox, null);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, mCheckBoxAccessoryHeight);
			mAccessoryView.addView(mCheckBoxAccessory, params);
		} else if (mAccessoryType == ACCESSORY_RADIO) {
			mAccessoryView.removeAllViews();
			mRadioAccessory = (RadioButton) mInflater.inflate(R.layout.accessory_radio_button, null);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, mRadioAccessoryHeight);
			mAccessoryView.addView(mRadioAccessory, params);
		} else {
			mAccessoryView.removeAllViews();
		}
	}
	
	public int getAccessoryType() {
		return mAccessoryType;
	}
	
	public void hideAccessory() {
		mAccessoryView.setVisibility(View.GONE);
	}
	
	public void showAccessory() {
		mAccessoryView.setVisibility(View.VISIBLE);
	}
	
	@Override
	public boolean isClickable() {
		return mItemContainer.isClickable();
	}

	@Override
	public void setClickable(boolean clickable) {
		mItemContainer.setClickable(clickable);
	}
	
	public interface OnItemClickListener {
		void onItemClick(UITableViewItem item);
	}

	public void setOnItemClickListener(final OnItemClickListener listener) {
		if (mItemContainer.isClickable()) {
			mItemContainer.setClickable(true);
			mItemContainer.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					listener.onItemClick(UITableViewItem.this);
				}
			});
		} else {
			mItemContainer.setClickable(false);
		}
	}
	
	public interface OnButtonAccessoryClickListener {
		public void onClick(UITableViewItem item);
	}
	
	public void setOnButtonAccessoryClickListener(final OnButtonAccessoryClickListener listener) {
		if (mButtonAccessory != null) {
			mButtonAccessory.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					listener.onClick(UITableViewItem.this);
				}
			});
		}
	}
	
	public interface OnSwitchAccessoryChangeListener {
		public void onCheckedChanged(UITableViewItem item, boolean isChecked);
	}
	
	public void setOnSwitchAccessoryChangeListener(final OnSwitchAccessoryChangeListener listener) {
		if (mSwitchAccessory != null) {
			mSwitchAccessory.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					listener.onCheckedChanged(UITableViewItem.this, isChecked);
				}
			});
		}
	}
	
	public interface OnCheckBoxAccessoryChangeListener {
		public void onCheckedChanged(UITableViewItem item, boolean isChecked);
	}
	
	public void setOnCheckBoxAccessoryChangeListener(final OnCheckBoxAccessoryChangeListener listener) {
		if (mCheckBoxAccessory != null) {
			mCheckBoxAccessory.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					listener.onCheckedChanged(UITableViewItem.this, isChecked);
				}
			});
		}
	}
	
	public interface OnRadioAccessoryChangeListener {
		public void onCheckedChanged(UITableViewItem item, boolean isChecked);
	}
	
	public void setOnRadioAccessoryChangeListener(final OnRadioAccessoryChangeListener listener) {
		if (mRadioAccessory != null) {
			mRadioAccessory.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					listener.onCheckedChanged(UITableViewItem.this, isChecked);
				}
			});
		}
	}
}

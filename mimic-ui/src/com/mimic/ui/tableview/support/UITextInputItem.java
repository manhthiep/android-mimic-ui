package com.mimic.ui.tableview.support;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimic.ui.R;
import com.mimic.ui.tableview.UITableViewItem;

public class UITextInputItem extends UITableViewItem {
	
	private TextView mLabel = null;
	private EditText mInputText = null;
	
	public UITextInputItem(Context context, String label, String hint) {
		super(context, label);
		setStyle(STYLE_NONE);
		setupTextInputViews();
		setLabel(label);
		setHint(hint);
		setInputType(InputType.TYPE_CLASS_TEXT);
	}
	
	public UITextInputItem(Context context, String label, String hint, int inputType) {
		super(context, label);
		setStyle(STYLE_NONE);
		setupTextInputViews();
		setLabel(label);
		setHint(hint);
		setInputType(inputType);
	}
	
	void setupTextInputViews() {
		LinearLayout textInputItemContentView = (LinearLayout) getInflater().inflate(
				R.layout.uitextinputitem, null);
		mLabel = (TextView) textInputItemContentView.findViewById(R.id.label);
		mInputText = (EditText) textInputItemContentView.findViewById(R.id.text);
		getContentView().removeAllViews();
		getContentView().addView(textInputItemContentView);
		/*
		mItemContainer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mInputText.requestFocus();
			}
		});		
		*/
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

	public EditText getInputTextView() {
		return mInputText;
	}
	
	public void setHint(String hint) {
		if (hint != null) {
			mInputText.setHint(hint);
		}
	}
	
	public String getInputText() {
		return mInputText.getText().toString();
	}

	public int getInputType() {
		return mInputText.getInputType();
	}
	
	public void setInputType(int inputType) {
		mInputText.setInputType(inputType);
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

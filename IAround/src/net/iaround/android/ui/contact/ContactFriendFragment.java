package net.iaround.android.ui.contact;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ContactFriendFragment extends ContactFriendFragmentBase {

	public ContactFriendFragment(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.contact_friend, null);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mEnter = (EditText) findViewById(R.id.contact_friend_enter);
		mClear = (Button) findViewById(R.id.contact_friend_clear);
		mDisplay = (ListView) findViewById(R.id.contact_friend_display);
		mShadow = findViewById(R.id.contact_friend_shadow);
	}

	@Override
	protected void setListener() {
		mEnter.setOnFocusChangeListener(this);
		mClear.setOnClickListener(this);
		mShadow.setOnClickListener(this);
	}

	@Override
	protected void init() {
		getFriend();
		mAdapter = new Adapter();
		mDisplay.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		mEnter.clearFocus();
		((InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(mActivity.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		if (v.getId() == R.id.contact_friend_clear) {
			mEnter.setText("");
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			mShadow.setVisibility(View.VISIBLE);
			mClear.setVisibility(View.VISIBLE);
		} else {
			mShadow.setVisibility(View.GONE);
			mClear.setVisibility(View.GONE);
		}
	}

}

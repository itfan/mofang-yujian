package net.iaround.android.ui.contact;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContactFragment extends ContactFragmentBase {

	public ContactFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.contact, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.contact_action_bar);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mIarActionBar.setOnMenuClickListener(this);
	}

	@Override
	protected void init() {
		mTitlePopupwindowAdapter = new TitlePopupwindowAdapter();
		mIarActionBar.setTitleAdapter(mTitlePopupwindowAdapter);
		mIarActionBar.setPopupWindowMode(IARActionBar.MODE_LARGE);
		initContent();
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	@Override
	public void onMenuClick() {
		if (mContactChatFragment.bottomIsShowing()) {
			mContactChatFragment.dismissBottom();
		} else {
			mContactChatFragment.showBottom();
		}
	}

}

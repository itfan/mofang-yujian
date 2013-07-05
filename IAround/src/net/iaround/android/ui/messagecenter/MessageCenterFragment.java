package net.iaround.android.ui.messagecenter;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MessageCenterFragment extends MessageCenterFragmentBase {

	public MessageCenterFragment(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.messagecenter, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
		mDisplay = (ListView) findViewById(R.id.display);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
	}

	@Override
	protected void init() {
		getMessageCenter();
		mAdapter = new Adapter();
		mDisplay.setAdapter(mAdapter);
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

}

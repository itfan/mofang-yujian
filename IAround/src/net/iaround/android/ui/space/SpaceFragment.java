package net.iaround.android.ui.space;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SpaceFragment extends SpaceFragmentBase {

	public SpaceFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.space, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mContent = findViewById(R.id.content);
		mIvAvatar = (ImageView) findViewById(R.id.avatar);
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void init() {
		initHead();
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}
}

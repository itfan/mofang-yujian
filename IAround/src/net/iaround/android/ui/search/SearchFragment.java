package net.iaround.android.ui.search;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class SearchFragment extends SearchFragmentBase {

	public SearchFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.search, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
		mAges = (TextView) findViewById(R.id.ages);
		mGenders = (TextView) findViewById(R.id.genders);
		mDistances = (TextView) findViewById(R.id.distances);
		mOnline = (CheckedTextView) findViewById(R.id.online);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mIarActionBar.setOnMenuClickListener(this);
		mAges.setOnClickListener(this);
		mGenders.setOnClickListener(this);
		mDistances.setOnClickListener(this);
		mOnline.setOnClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ages:
			showAgesDialog();
			break;

		case R.id.genders:
			showGendersDialog();
			break;
			
		case R.id.distances:
			showDistancesDialog();
			break;
			
		case R.id.online:
			mOnline.toggle();
			break;
		}
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	@Override
	public void onMenuClick() {
		showShortToast("暂时不支持搜索");
	}

}

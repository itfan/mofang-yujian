package net.iaround.android.ui.focus;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.IARFocusTitle;
import net.iaround.android.ui.common.IARGridView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FocusFragment extends FocusFragmentBase {

	public FocusFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.focus, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.focus_action_bar);
		mIarFocusTitle = (IARFocusTitle) findViewById(R.id.focus_title);
		mIarGridView = (IARGridView) findViewById(R.id.focus_grid);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
	}

	@Override
	protected void init() {
		getFocus();
		mIarGridView.setAdapter(new GridAdapter());
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}
}

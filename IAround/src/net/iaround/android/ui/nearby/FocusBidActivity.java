package net.iaround.android.ui.nearby;

import net.iaround.android.R;
import net.iaround.android.ui.base.AFocusBidActivity;
import net.iaround.android.ui.common.IARActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FocusBidActivity extends AFocusBidActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.focusbid_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.focusbid_action_bar);
		mTvTitle1 = (TextView) findViewById(R.id.focusbid_title1);
		mTvTitle2 = (TextView) findViewById(R.id.focusbid_title2);
		mTvTitle3 = (TextView) findViewById(R.id.focusbid_title3);
		mBtnBid = (Button) findViewById(R.id.focusbid_bid);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mBtnBid.setOnClickListener(this);
	}

	@Override
	protected void init() {
		mTvTitle1.setText(getStringFromRes(R.string.nearby_focus_directions_1));
		mTvTitle2.setText(getStringFromRes(R.string.nearby_focus_directions_2));
		mTvTitle3.setText(getStringFromRes(R.string.nearby_focus_price));
	}

	@Override
	public void onClick(View v) {
		showLogError("onClick", "出价");
	}

	@Override
	public void onBackClick() {
		finish();
	}

}

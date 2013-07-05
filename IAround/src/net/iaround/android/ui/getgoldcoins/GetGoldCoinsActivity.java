package net.iaround.android.ui.getgoldcoins;

import net.iaround.android.R;
import net.iaround.android.ui.base.AGetGoldCoinsActivity;
import net.iaround.android.ui.common.IARActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TitlePageIndicator;

public class GetGoldCoinsActivity extends AGetGoldCoinsActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getgoldcoins_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.getgoldcoins_action_bar);
		mIndicator = (TitlePageIndicator) findViewById(R.id.getgoldcoins_indicator);
		mPager = (ViewPager) findViewById(R.id.getgoldcoins_pager);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
	}

	@Override
	protected void init() {
		initPager();
		initCurrentGoldCoinsCount(mCurrentGoldCoinsCount);
		Bundle bundle = getIntent().getExtras();
		int type = bundle.getInt(INTENT_TYPE);
		mPager.setCurrentItem(type);
	}

	@Override
	public void onBackClick() {
		finish();
	}

	@Override
	public void onBackPressed() {
		finish();
	}
}

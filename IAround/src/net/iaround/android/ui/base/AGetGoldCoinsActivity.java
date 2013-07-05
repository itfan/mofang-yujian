package net.iaround.android.ui.base;

import java.util.ArrayList;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.ui.getgoldcoins.BuyFragment;
import net.iaround.android.ui.getgoldcoins.ContinuFragment;
import net.iaround.android.ui.getgoldcoins.SinaFragment;
import net.iaround.android.ui.getgoldcoins.TotalFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitleProvider;

public abstract class AGetGoldCoinsActivity extends FragmentActivity implements
		OnBackClickListener {
	/** Appliction基类对象 **/
	protected BaseApplication mApplication;

	protected IARActionBar mIarActionBar;
	protected TitlePageIndicator mIndicator;
	protected ViewPager mPager;
	protected FragmentPagerAdapter mAdapter;
	protected String[] mTitles;

	protected TotalFragment mTotalFragment;
	protected ContinuFragment mContinuFragment;
	protected BuyFragment mBuyFragment;
	protected SinaFragment mSinaFragment;

	protected static int mCurrentGoldCoinsCount = 550;

	public static final int INTENT_TOTAL = 0;
	public static final int INTENT_CONTINU = 1;
	public static final int INTENT_BUY = 2;
	public static final int INTENT_SINA = 3;
	public static final String INTENT_TYPE = "type";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取BaseApplication对象
		mApplication = (BaseApplication) getApplication();
		mTitles = new String[] { getString(R.string.pay_title_total),
				getString(R.string.pay_title_continu),
				getString(R.string.pay_title_buy),
				getString(R.string.pay_title_sina) };

	}

	/** 绑定界面UI **/
	protected abstract void findViewById();

	/** 界面UI事件监听 **/
	protected abstract void setListener();

	/** 界面数据初始化 **/
	protected abstract void init();

	protected void initPager() {
		if (mApplication.mGetGoldCoinsFragments == null) {
			mApplication.mGetGoldCoinsFragments = new ArrayList<Fragment>();
		}
		if (mApplication.mGetGoldCoinsFragments.isEmpty()) {
			mTotalFragment = new TotalFragment(mApplication,
					AGetGoldCoinsActivity.this, AGetGoldCoinsActivity.this);
			mContinuFragment = new ContinuFragment(mApplication,
					AGetGoldCoinsActivity.this, AGetGoldCoinsActivity.this);
			mBuyFragment = new BuyFragment(mApplication,
					AGetGoldCoinsActivity.this, AGetGoldCoinsActivity.this);
			mSinaFragment = new SinaFragment(mApplication,
					AGetGoldCoinsActivity.this, AGetGoldCoinsActivity.this);
			mApplication.mGetGoldCoinsFragments.add(mTotalFragment);
			mApplication.mGetGoldCoinsFragments.add(mContinuFragment);
			mApplication.mGetGoldCoinsFragments.add(mBuyFragment);
			mApplication.mGetGoldCoinsFragments.add(mSinaFragment);
		}
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mPager);
	}

	protected void initCurrentGoldCoinsCount(int count) {
		String s = String.format(getString(R.string.pay_current_count),
				String.valueOf(count));
		mIarActionBar.setTitle3(Html.fromHtml(s));
	}

	public void setCurrentGoldCoinsCount(int count) {
		mCurrentGoldCoinsCount = count;
		initCurrentGoldCoinsCount(count);
	}

	public int getCurrentGoldCoinsCount() {
		return mCurrentGoldCoinsCount;
	}

	protected class FragmentPagerAdapter extends
			android.support.v4.app.FragmentPagerAdapter implements
			TitleProvider {

		public FragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return mApplication.mGetGoldCoinsFragments.get(arg0);
		}

		@Override
		public int getCount() {
			if (mApplication.mGetGoldCoinsFragments == null) {
				return 0;
			} else {
				return mApplication.mGetGoldCoinsFragments.size();
			}
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public String getTitle(int position) {
			return mTitles[position];
		}
	}

	/** 带有右进右出动画的退出 **/
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}
}

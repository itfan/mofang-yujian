package net.iaround.android.ui.getgoldcoins;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public abstract class TotalFragmentBase extends BaseFragment implements
		OnClickListener {

	protected TextView mOnlineTime;
	protected TextView mTitleVip;
	protected TextView mTitleMember;
	protected TextView mText1;
	protected Button mBtn1;
	protected TextView mText2;
	protected Button mBtn2;
	protected TextView mText3;
	protected Button mBtn3;
	protected TextView mText4;
	protected Button mBtn4;
	protected TextView mText5;
	protected Button mBtn5;

	private static final int MINUTE_5 = 5;
	private static final int MINUTE_10 = 10;
	private static final int MINUTE_30 = 30;
	private static final int MINUTE_90 = 90;
	private static final int MINUTE_120 = 120;
	private static final int ONEHOUR = 60 * 60 * 1000;
	private static final int ONEMINUTE = 60 * 1000;
	private static final String VIP = "55";
	private static final String MEMBER = "50";

	protected long mCurrentTime;
	protected int mOnlineHour;
	protected int mOnlineMinute;
	protected boolean mReward_5;
	protected boolean mReward_10;
	protected boolean mReward_30;
	protected boolean mReward_90;
	protected boolean mReward_120;

	public TotalFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurrentTime = System.currentTimeMillis();
		long time = mCurrentTime - mApplication.mLoginTime;
		mOnlineHour = (int) (time / ONEHOUR);
		mOnlineMinute = (int) ((time % ONEHOUR) / ONEMINUTE);
		initOnlineTime();
		initContent();
	}

	protected void initOnlineTime() {
		String s = String.format(
				getString(R.string.pay_total_str1),
				new Object[] { String.valueOf(mOnlineHour),
						String.valueOf(mOnlineMinute) });
		mOnlineTime.setText(s);
	}

	protected void initTitle() {
		String vip = String.format(getString(R.string.pay_total_vipgold), VIP);
		String member = String.format(getString(R.string.pay_total_gold),
				MEMBER);
		mTitleVip.setText(Html.fromHtml(vip));
		mTitleMember.setText(Html.fromHtml(member));
	}

	protected void initContent() {
		mText1.setText(getFormat(MINUTE_5));
		mText2.setText(getFormat(MINUTE_10));
		mText3.setText(getFormat(MINUTE_30));
		mText4.setText(getFormat(MINUTE_90));
		mText5.setText(getFormat(MINUTE_120));
		if (mOnlineHour * 60 + mOnlineMinute >= MINUTE_5) {
			if (mReward_5) {
				mBtn1.setText(getString(R.string.pay_total_str6));
				mBtn1.setBackgroundResource(R.drawable.pay_total_get_btn);
				mBtn1.setEnabled(false);
			} else {
				mBtn1.setText(getString(R.string.pay_total_str7));
				mBtn1.setBackgroundResource(R.drawable.pay_total_get_btn2);
				mBtn1.setEnabled(true);
			}
		} else {
			mBtn1.setText(getString(R.string.pay_total_str8));
			mBtn1.setBackgroundResource(R.drawable.pay_total_get_btn);
			mBtn1.setEnabled(false);
		}

		if (mOnlineHour * 60 + mOnlineMinute >= MINUTE_10) {
			if (mReward_10) {
				mBtn2.setText(getString(R.string.pay_total_str6));
				mBtn2.setBackgroundResource(R.drawable.pay_total_get_btn);
				mBtn2.setEnabled(false);
			} else {
				mBtn2.setText(getString(R.string.pay_total_str7));
				mBtn2.setBackgroundResource(R.drawable.pay_total_get_btn2);
				mBtn2.setEnabled(true);
			}
		} else {
			mBtn2.setText(getString(R.string.pay_total_str8));
			mBtn2.setBackgroundResource(R.drawable.pay_total_get_btn);
			mBtn2.setEnabled(false);
		}

		if (mOnlineHour * 60 + mOnlineMinute >= MINUTE_30) {
			if (mReward_30) {
				mBtn3.setText(getString(R.string.pay_total_str6));
				mBtn3.setBackgroundResource(R.drawable.pay_total_get_btn);
				mBtn3.setEnabled(false);
			} else {
				mBtn3.setText(getString(R.string.pay_total_str7));
				mBtn3.setBackgroundResource(R.drawable.pay_total_get_btn2);
				mBtn3.setEnabled(true);
			}
		} else {
			mBtn3.setText(getString(R.string.pay_total_str8));
			mBtn3.setBackgroundResource(R.drawable.pay_total_get_btn);
			mBtn3.setEnabled(false);
		}

		if (mOnlineHour * 60 + mOnlineMinute >= MINUTE_90) {
			if (mReward_90) {
				mBtn4.setText(getString(R.string.pay_total_str6));
				mBtn4.setBackgroundResource(R.drawable.pay_total_get_btn);
				mBtn4.setEnabled(false);
			} else {
				mBtn4.setText(getString(R.string.pay_total_str7));
				mBtn4.setBackgroundResource(R.drawable.pay_total_get_btn2);
				mBtn4.setEnabled(true);
			}
		} else {
			mBtn4.setText(getString(R.string.pay_total_str8));
			mBtn4.setBackgroundResource(R.drawable.pay_total_get_btn);
			mBtn4.setEnabled(false);
		}
		if (mOnlineHour * 60 + mOnlineMinute >= MINUTE_120) {
			if (mReward_120) {
				mBtn5.setText(getString(R.string.pay_total_str6));
				mBtn5.setBackgroundResource(R.drawable.pay_total_get_btn);
				mBtn5.setEnabled(false);
			} else {
				mBtn5.setText(getString(R.string.pay_total_str7));
				mBtn5.setBackgroundResource(R.drawable.pay_total_get_btn2);
				mBtn5.setEnabled(true);
			}
		} else {
			mBtn5.setText(getString(R.string.pay_total_str8));
			mBtn5.setBackgroundResource(R.drawable.pay_total_get_btn);
			mBtn5.setEnabled(false);
		}
	}

	protected String getFormat(int s) {
		return String.format(getString(R.string.pay_total_str5),
				String.valueOf(s));
	}

	protected void setTotalCount() {
		int totalCount = ((GetGoldCoinsActivity) getActivity())
				.getCurrentGoldCoinsCount() + 50;
		((GetGoldCoinsActivity) getActivity())
				.setCurrentGoldCoinsCount(totalCount);
	}
}

package net.iaround.android.ui.getgoldcoins;

import android.app.Activity;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;

public abstract class ContinuFragmentBase extends BaseFragment implements
		OnClickListener {

	protected TextView mTitle;
	protected Button mGet;
	protected boolean isGet;
	protected int mGetGoldCoinsCount;

	public ContinuFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public void onResume() {
		super.onResume();
		initBtn();
	}

	protected void initTitle() {
		mTitle.setText(String.format(getString(R.string.pay_login_str1),
				String.valueOf(mGetGoldCoinsCount)));
	}

	protected void initBtn() {
		if (isGet) {
			mGet.setText(getString(R.string.pay_total_str6));
			mGet.setBackgroundResource(R.drawable.pay_login_btn_bg2);
		} else {
			mGet.setText(getString(R.string.pay_total_str7));
			mGet.setBackgroundResource(R.drawable.pay_login_btn_bg);
		}
	}

	protected void setTotalCount() {
		mGetGoldCoinsCount = 100;
		initTitle();
		int totalCount = ((GetGoldCoinsActivity) getActivity())
				.getCurrentGoldCoinsCount() + mGetGoldCoinsCount;
		((GetGoldCoinsActivity) getActivity())
				.setCurrentGoldCoinsCount(totalCount);
	}
}

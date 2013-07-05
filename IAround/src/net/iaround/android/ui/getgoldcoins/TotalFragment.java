package net.iaround.android.ui.getgoldcoins;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TotalFragment extends TotalFragmentBase {

	public TotalFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.getgoldcoins_total, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mOnlineTime = (TextView) findViewById(R.id.total_online_time);
		mTitleVip = (TextView) findViewById(R.id.total_title_vip);
		mTitleMember = (TextView) findViewById(R.id.total_title_member);
		mText1 = (TextView) findViewById(R.id.total_text_1);
		mBtn1 = (Button) findViewById(R.id.total_btn_1);
		mText2 = (TextView) findViewById(R.id.total_text_2);
		mBtn2 = (Button) findViewById(R.id.total_btn_2);
		mText3 = (TextView) findViewById(R.id.total_text_3);
		mBtn3 = (Button) findViewById(R.id.total_btn_3);
		mText4 = (TextView) findViewById(R.id.total_text_4);
		mBtn4 = (Button) findViewById(R.id.total_btn_4);
		mText5 = (TextView) findViewById(R.id.total_text_5);
		mBtn5 = (Button) findViewById(R.id.total_btn_5);
	}

	@Override
	protected void setListener() {
		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);
		mBtn3.setOnClickListener(this);
		mBtn4.setOnClickListener(this);
		mBtn5.setOnClickListener(this);
	}

	@Override
	protected void init() {
		initTitle();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.total_btn_1:
			mReward_5 = true;
			break;

		case R.id.total_btn_2:
			mReward_10 = true;
			break;

		case R.id.total_btn_3:
			mReward_30 = true;
			break;

		case R.id.total_btn_4:
			mReward_90 = true;
			break;

		case R.id.total_btn_5:
			mReward_120 = true;
			break;
		}
		setTotalCount();
		((Button) v).setText(getString(R.string.pay_total_str6));
		((Button) v).setBackgroundResource(R.drawable.pay_total_get_btn);
		((Button) v).setEnabled(false);
		showShortToast(R.string.pay_getgold_success);
	}
}

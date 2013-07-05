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

public class ContinuFragment extends ContinuFragmentBase {

	public ContinuFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.getgoldcoins_continu, container,
				false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mTitle=(TextView)findViewById(R.id.continu_title);
		mGet=(Button)findViewById(R.id.continu_get);
	}

	@Override
	protected void setListener() {
		mGet.setOnClickListener(this);
	}

	@Override
	protected void init() {
		initTitle();
	}

	@Override
	public void onClick(View v) {
		if (!isGet) {
			isGet=true;
			setTotalCount();
			mGet.setText(getString(R.string.pay_total_str6));
			mGet.setBackgroundResource(R.drawable.pay_login_btn_bg2);
			showShortToast(R.string.pay_getgold_success);
		}
	}
}

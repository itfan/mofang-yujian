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

public class BuyFragment extends BuyFragmentBase {

	public BuyFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.getgoldcoins_buy, container, false);
		return mView;
	}

	@Override
	protected void findViewById() {
		mBtn1 = (Button) findViewById(R.id.buy_btn1);
		mBtn2 = (Button) findViewById(R.id.buy_btn2);
		mBtn3 = (Button) findViewById(R.id.buy_btn3);
		mBtn4 = (Button) findViewById(R.id.buy_btn4);
		mBtn5 = (Button) findViewById(R.id.buy_btn5);
		mBtn6 = (Button) findViewById(R.id.buy_btn6);
		mBtn7 = (Button) findViewById(R.id.buy_btn7);
	}

	@Override
	protected void setListener() {
		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);
		mBtn3.setOnClickListener(this);
		mBtn4.setOnClickListener(this);
		mBtn5.setOnClickListener(this);
		mBtn6.setOnClickListener(this);
		mBtn7.setOnClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onClick(View v) {

	}

}

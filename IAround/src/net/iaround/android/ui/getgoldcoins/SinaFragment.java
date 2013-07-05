package net.iaround.android.ui.getgoldcoins;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SinaFragment extends SinaFragmentBase {

	public SinaFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.getgoldcoins_sina, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mTitleVip = (TextView) findViewById(R.id.sina_title_vip);
		mTitleMember = (TextView) findViewById(R.id.sina_title_member);
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void init() {
		initTitle();
	}

}

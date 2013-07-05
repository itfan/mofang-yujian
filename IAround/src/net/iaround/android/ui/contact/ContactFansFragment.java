package net.iaround.android.ui.contact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;

public class ContactFansFragment extends BaseFragment {

	public ContactFansFragment(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.contact_fans, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {

	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void init() {

	}

}

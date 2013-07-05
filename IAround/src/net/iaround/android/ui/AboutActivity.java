package net.iaround.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import net.iaround.android.R;
import net.iaround.android.ui.base.AAbountActivity;
import net.iaround.android.ui.common.IARActionBar;

/**
 * @fileName AboutActivity.java
 * @package net.iaround.android.ui
 * @description 关于界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class AboutActivity extends AAbountActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.about_action_bar);
		mBtnNewFeatures = (Button) findViewById(R.id.about_new_features);
		mBtnVisitAppWebsite = (Button) findViewById(R.id.about_visit_app_website);
		mBtnVisitAppWeiBo = (Button) findViewById(R.id.about_visit_app_weibo);
		mBtnCustomerServiceHotline = (Button) findViewById(R.id.about_customer_service_hotline);
		mBtnCustomerServiceMail = (Button) findViewById(R.id.about_customer_service_mail);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mBtnNewFeatures.setOnClickListener(this);
		mBtnVisitAppWebsite.setOnClickListener(this);
		mBtnVisitAppWeiBo.setOnClickListener(this);
		mBtnCustomerServiceHotline.setOnClickListener(this);
		mBtnCustomerServiceMail.setOnClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_new_features:

			break;

		case R.id.about_visit_app_website:
			openWebsite();
			break;

		case R.id.about_visit_app_weibo:
			openWeiBo();
			break;

		case R.id.about_customer_service_hotline:
			call();
			break;

		case R.id.about_customer_service_mail:
			sendTo();
			break;
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public void onBackClick() {
		finish();
	}

}

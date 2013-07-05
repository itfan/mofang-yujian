package net.iaround.android.ui.vip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import net.iaround.android.R;
import net.iaround.android.ui.base.AVipPrivilegeActivity;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.getgoldcoins.GetGoldCoinsActivity;

/**
 * @fileName VipPrivilegeActivity.java
 * @package net.iaround.android.ui.vip
 * @description VIP特权类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class VipPrivilegeActivity extends AVipPrivilegeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vipprivilege_acitivty);
		// 获取内容头布局
		mHead = LayoutInflater.from(VipPrivilegeActivity.this).inflate(
				R.layout.vipprivilege_acitivty_head, null);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
		mDisplay = (ListView) findViewById(R.id.list);
		mGetGold = (Button) findViewById(R.id.getgold);
		mHeadAvatar = (ImageView) findViewById(R.id.avatar);
		mHeadTitle1 = (TextView) findViewById(R.id.title1);
		mHeadTitle2 = (TextView) findViewById(R.id.title2);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mGetGold.setOnClickListener(this);
	}

	@Override
	protected void init() {
		getVipPrivilege();
		mHeadAvatar.setImageResource(R.drawable.head);
		mHeadTitle1.setText(getStringFromRes(R.string.vip_showvip1));
		mHeadTitle2.setText(getStringFromRes(R.string.vip_showvip2));
		mDisplay.addHeaderView(mHead);
		mAdapter = new Adapter();
		mDisplay.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.getgold:
			Bundle bundle = new Bundle();
			bundle.putInt(GetGoldCoinsActivity.INTENT_TYPE,
					GetGoldCoinsActivity.INTENT_BUY);
			startActivity(GetGoldCoinsActivity.class, bundle);
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackClick() {
		finish();
	}

}

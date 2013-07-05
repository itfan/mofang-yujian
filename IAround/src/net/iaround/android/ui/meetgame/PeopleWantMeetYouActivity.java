package net.iaround.android.ui.meetgame;

import android.os.Bundle;
import net.iaround.android.R;
import net.iaround.android.ui.base.APeopleWantMeetYouActivity;
import net.iaround.android.ui.common.IARActionBar;
/**
 * @fileName PeopleWantMeetYou.java
 * @package net.iaround.android.ui.meetgame
 * @description 想见你的人
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class PeopleWantMeetYouActivity extends APeopleWantMeetYouActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.peoplewantmeetyou_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onBackClick() {
		finish();
	}
}

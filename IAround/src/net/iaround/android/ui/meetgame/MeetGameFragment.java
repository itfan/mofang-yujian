package net.iaround.android.ui.meetgame;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @fileName MeetGameFragment.java
 * @package net.iaround.android.ui.meetgame
 * @description 邂逅游戏
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class MeetGameFragment extends MeetGameFragmentBase {

	public MeetGameFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.meetgame, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.meet_action_bar);
		mSeeFile = (LinearLayout) findViewById(R.id.meet_see_file);
		mSendGift = (LinearLayout) findViewById(R.id.meet_send_gift);
		mWant = (LinearLayout) findViewById(R.id.meet_want);
		mNoWant = (LinearLayout) findViewById(R.id.meet_no_want);
		mCentre = findViewById(R.id.meet_centre);
		mCentreImage = (ImageView) findViewById(R.id.meet_game_centre_image);
		mCentreMatchingLayout = (RelativeLayout) findViewById(R.id.meet_game_centre_matching_layout);
		mCentreMatching = (TextView) findViewById(R.id.meet_game_centre_matching);
		mCentreAppearanceLayout = (LinearLayout) findViewById(R.id.meet_game_centre_appearance_layout);
		mCentreAppearance = (TextView) findViewById(R.id.meet_game_centre_appearance);
		mCentreFateMatchingLayout = (LinearLayout) findViewById(R.id.meet_game_centre_fate_matching_layout);
		mCentreFateMatching = (TextView) findViewById(R.id.meet_game_centre_fate_matching);
		mCentreConstellationLayout = (LinearLayout) findViewById(R.id.meet_game_centre_constellation_layout);
		mCentreConstellation = (TextView) findViewById(R.id.meet_game_centre_constellation);
		mCentreProgress = (ProgressBar) findViewById(R.id.meet_game_centre_progress);
		mCentreTitle = (TextView) findViewById(R.id.meet_game_centre_title);
		mCentreSexAgeLayout = (RelativeLayout) findViewById(R.id.meet_game_centre_sex_age_layout);
		mCentreAge = (TextView) findViewById(R.id.meet_game_centre_age);
		mCentreSex = (ImageView) findViewById(R.id.meet_game_centre_sex);
		mCentreSignature = (TextView) findViewById(R.id.meet_game_centre_signature);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mIarActionBar.setOnMenuClickListener(this);
		mSeeFile.setOnClickListener(this);
		mSendGift.setOnClickListener(this);
		mWant.setOnClickListener(this);
		mNoWant.setOnClickListener(this);
	}

	@Override
	protected void init() {
		getMeetGame();
		initMeetGame();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.meet_see_file:
			showNoVipDialog();
			break;

		case R.id.meet_send_gift:
			showNoVipDialog();
			break;

		case R.id.meet_want:
			showShortToast(R.string.meet_message_send);
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mIsNeedInit = false;
					startNext();
				}
			}, 1000);
			break;

		case R.id.meet_no_want:
			mIsNeedInit = false;
			startNext();
			break;
		}
	}

	@Override
	public void interpolatedTime(float interpolatedTime) {
		if (interpolatedTime > 0.5f && mIsNeedInit == false) {
			initMeetGame();
		}
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	@Override
	public void onMenuClick() {
		startActivity(PeopleWantMeetYouActivity.class);
	}
}

package net.iaround.android.ui.nearby;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.IARGridView;
import net.iaround.android.ui.common.IARListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class NearByFragment extends NearByFragmentBase {
	public NearByFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.nearby, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
		mScrollView = (HorizontalScrollView) findViewById(R.id.scrollview);
		mFocus = (LinearLayout) findViewById(R.id.focus);
		mIarListView = (IARListView) findViewById(R.id.list);
		mIarGridView = (IARGridView) findViewById(R.id.grid);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mScrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_MOVE:
					handler.removeMessages(1);
					mIsSlide = true;
					handler.sendEmptyMessage(0);
					break;

				case MotionEvent.ACTION_UP:
					handler.sendEmptyMessageDelayed(1, 1000);
					break;
				}
				return false;
			}
		});
		mFocus.setOnClickListener(this);
	}

	@Override
	protected void init() {
		mTitlePopupwindowAdapter = new TitlePopupwindowAdapter();
		mIarActionBar.setTitleAdapter(mTitlePopupwindowAdapter);
		mIarActionBar.setPopupWindowMode(IARActionBar.MODE_NONE);
		initNearBy();
	}

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				if (mIsShow) {
					mIsShow = false;
					mFocus.startAnimation(getLeftOutAnimation(mFocus));
				}
				break;

			case 1:
				if (mIsSlide && mIsShow == false) {
					mIsSlide = false;
					mIsShow = true;
					mFocus.startAnimation(getLeftInAnimation(mFocus));
				}
				break;
			}
		}
	};

	public void slidingMenuOpen() {
		handler.sendEmptyMessageDelayed(1, 1000);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.focus:
			if (mIsShow) {
				startActivity(FocusBidActivity.class);
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}
}

package net.iaround.android.ui.space;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.ui.common.OnBackClickListener;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * @fileName SpaceFragment.java
 * @package net.iaround.android.ui.space
 * @description 我的资料基类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class SpaceFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener {
	protected View mContent;
	protected ImageView mIvAvatar;

	protected int mScreenWidth;

	public SpaceFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
		DisplayMetrics metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;

	}

	protected View findViewById(int id) {
		if (mContent != null) {
			if (mContent.findViewById(id) != null) {
				return mContent.findViewById(id);
			}
		}
		return super.findViewById(id);
	}

	protected void initHead() {
		mIvAvatar.getLayoutParams().width = mScreenWidth - 40;
		mIvAvatar.getLayoutParams().height = mScreenWidth - 40;
	}
}

package net.iaround.android.ui.base;

import net.iaround.android.BaseActivity;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @fileName AWebActivity.java
 * @package net.iaround.android.ui.base
 * @description 网页界面父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class AWebActivity extends BaseActivity implements
		OnBackClickListener {
	/** 自定义标题栏 **/
	protected IARActionBar mIarActionBar;
	/** 网页内容显示 **/
	protected WebView mWvWeb;
	/** 进度条 **/
	protected ProgressBar mPbProgress;
	/** 网页设置 **/
	protected WebSettings mSettings;

	/** 显示的标题内容 **/
	protected String mTitle;
	/** 网页的URL地址 **/
	protected String mUrl;

	/** 显示进度条 **/
	protected void showProgress() {
		mPbProgress.setVisibility(View.VISIBLE);
	}

	/** 隐藏进度条 **/
	protected void dismissProgress() {
		mPbProgress.setVisibility(View.GONE);
	}

}

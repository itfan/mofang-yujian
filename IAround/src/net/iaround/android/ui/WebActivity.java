package net.iaround.android.ui;

import net.iaround.android.R;
import net.iaround.android.ui.base.AWebActivity;
import net.iaround.android.ui.common.IARActionBar;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * @fileName WebActivity.java
 * @package net.iaround.android.ui
 * @description 网页界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class WebActivity extends AWebActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mIarActionBar = (IARActionBar) findViewById(R.id.action_bar);
		mWvWeb = (WebView) findViewById(R.id.web);
		mPbProgress = (ProgressBar) findViewById(R.id.progress);
	}

	@Override
	protected void setListener() {
		mIarActionBar.setOnBackClickListener(this);
		mWvWeb.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				mWvWeb.loadUrl(url);
				return true;
			}

			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, android.net.http.SslError error) {
				handler.proceed();
			}

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				showProgress();
			}

			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				dismissProgress();
			}
		});
	}

	@Override
	protected void init() {
		// 获取传递的Bundle
		Bundle bundle = getIntent().getExtras();
		// 获取标题内容
		mTitle = bundle.getString("title");
		// 获取网页URL地址
		mUrl = bundle.getString("url");
		// 标题不能空时添加标题
		if (!TextUtils.isEmpty(mTitle)) {
			mIarActionBar.setTitle(mTitle);
		}
		// URL地址不为空时加载地址
		if (!TextUtils.isEmpty(mUrl)) {
			mSettings = mWvWeb.getSettings();
			mSettings.setJavaScriptEnabled(true);
			mSettings.setBuiltInZoomControls(true);
			mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
			mWvWeb.loadUrl(mUrl);
		}
	}

	@Override
	public void onBackClick() {
		finish();
	}
}

package net.iaround.android.ui.base;

import android.text.Html;
import android.text.Spanned;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import net.iaround.android.BaseActivity;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;

public abstract class AFocusBidActivity extends BaseActivity implements
		OnClickListener, OnBackClickListener {
	protected IARActionBar mIarActionBar;
	protected TextView mTvTitle1;
	protected TextView mTvTitle2;
	protected TextView mTvTitle3;
	protected Button mBtnBid;

	protected Spanned getStringFromRes(int resId) {
		return Html.fromHtml(getString(resId));
	}
}

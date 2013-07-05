package net.iaround.android.ui.getgoldcoins;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.widget.TextView;
import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;

public abstract class SinaFragmentBase extends BaseFragment{
	protected TextView mTitleVip;
	protected TextView mTitleMember;
	private static final String VIP = "100";
	private static final String MEMBER = "80";
	public SinaFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}
	protected void initTitle() {
		String vip = String.format(getString(R.string.pay_total_vipgold), VIP);
		String member = String.format(getString(R.string.pay_total_gold),
				MEMBER);
		mTitleVip.setText(Html.fromHtml(vip));
		mTitleMember.setText(Html.fromHtml(member));
	}
}

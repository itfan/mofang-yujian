package net.iaround.android.ui.getgoldcoins;

import android.app.Activity;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;

public abstract class BuyFragmentBase extends BaseFragment implements
		OnClickListener {

	protected Button mBtn1;
	protected Button mBtn2;
	protected Button mBtn3;
	protected Button mBtn4;
	protected Button mBtn5;
	protected Button mBtn6;
	protected Button mBtn7;

	public BuyFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

}

package net.iaround.android.ui;

import net.iaround.android.R;
import net.iaround.android.ui.base.AFindPasswordActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

/**
 * @fileName FindPasswordActivity.java
 * @package net.iaround.android.ui
 * @description 找回密码界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class FindPasswordActivity extends AFindPasswordActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpassword_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mTvBack = (TextView) findViewById(R.id.findpassword_back);
		mActvEmail = (AutoCompleteTextView) findViewById(R.id.findpassword_email);
		mBtnReset = (Button) findViewById(R.id.findpassword_reset);
	}

	@Override
	protected void setListener() {
		mTvBack.setOnClickListener(this);
		mBtnReset.setOnClickListener(this);
		mActvEmail.addTextChangedListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.findpassword_back:
			finish();
			break;

		case R.id.findpassword_reset:

			break;
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		addAutoComplete(arg0.toString());
	}
}

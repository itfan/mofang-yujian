package net.iaround.android.ui;

import net.iaround.android.R;
import net.iaround.android.ui.base.ALoginActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @fileName LoginActivity.java
 * @package net.iaround.android.ui
 * @description 登录界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class LoginActivity extends ALoginActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mTvAbout = (TextView) findViewById(R.id.about);
		mActvAccount = (AutoCompleteTextView) findViewById(R.id.account);
		mEtPassword = (EditText) findViewById(R.id.password);
		mBtnLogin = (Button) findViewById(R.id.login);
		mBtnRegister = (Button) findViewById(R.id.register);
		mTvFindPassword = (TextView) findViewById(R.id.findpassword);
		mBtnQQ = (Button) findViewById(R.id.qq);
		mBtnWeiBo = (Button) findViewById(R.id.weibo);
		mBtnFaceBook = (Button) findViewById(R.id.facebook);
		mBtnTwitter = (Button) findViewById(R.id.twitter);
	}

	@Override
	protected void setListener() {
		mTvAbout.setOnClickListener(this);
		mBtnLogin.setOnClickListener(this);
		mBtnRegister.setOnClickListener(this);
		mBtnQQ.setOnClickListener(this);
		mBtnWeiBo.setOnClickListener(this);
		mBtnFaceBook.setOnClickListener(this);
		mBtnTwitter.setOnClickListener(this);

		mActvAccount.addTextChangedListener(this);
		mEtPassword.addTextChangedListener(this);
	}

	@Override
	protected void init() {
		addIntentLink();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about:
			startActivity(AboutActivity.class);
			break;

		case R.id.login:
			startActivity(MainActivity.class);
			finish();
			break;

		case R.id.register:

			break;

		case R.id.qq:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.weibo:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.facebook:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.twitter:
			showShortToast(R.string.authorization_is_not_supported);
			break;
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String account = mActvAccount.getText().toString().trim();
		String password = mEtPassword.getText().toString().trim();
		isInput(account, password);
	}

	public void onBackPressed() {
		showExitDialog();
	}
}

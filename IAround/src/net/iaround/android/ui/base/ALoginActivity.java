package net.iaround.android.ui.base;

import net.iaround.android.BaseActivity;
import net.iaround.android.R;
import net.iaround.android.ui.FindPasswordActivity;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @fileName ALoginActivity.java
 * @package net.iaround.android.ui.base
 * @description 登录界面父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class ALoginActivity extends BaseActivity implements
		OnClickListener, TextWatcher {
	/** 关于文本 **/
	protected TextView mTvAbout;
	/** 账号输入框 **/
	protected AutoCompleteTextView mActvAccount;
	/** 密码输入框 **/
	protected EditText mEtPassword;
	/** 登录按钮 **/
	protected Button mBtnLogin;
	/** 注册按钮 **/
	protected Button mBtnRegister;
	/** 找回密码文本 **/
	protected TextView mTvFindPassword;
	/** QQ授权按钮 **/
	protected Button mBtnQQ;
	/** 微博授权按钮 **/
	protected Button mBtnWeiBo;
	/** Facebook授权按钮 **/
	protected Button mBtnFaceBook;
	/** Twitter授权按钮 **/
	protected Button mBtnTwitter;

	/**
	 * 是否输入账号和密码
	 * 
	 * @param account
	 *            账号内容
	 * @param password
	 *            密码内容
	 */
	protected void isInput(String account, String password) {
		// 根据输入状态激活登录按钮
		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			mBtnLogin.setEnabled(false);
		} else {
			mBtnLogin.setEnabled(true);
		}
	}

	/** 忘记密码添加超链接 **/
	protected void addIntentLink() {
		String s = getString(R.string.find_password_new);
		SpannableString sp = new SpannableString(s);
		// 设置超链接内容以及显示的字体颜色
		sp.setSpan(new IntentSpan(new OnClickListener() {

			public void onClick(View view) {
				// 跳转到找回密码界面
				startActivity(FindPasswordActivity.class);
			}
		}), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(
				new ForegroundColorSpan(getResources().getColor(
						R.color.c_ff949494)), 0, s.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 设置文本内容
		mTvFindPassword.setText(sp);
		mTvFindPassword.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/** 退出对话框 **/
	protected void showExitDialog() {
		showAlertDialog(getString(R.string.prompt),
				getString(R.string.do_you_really_want_to_quit),
				android.R.drawable.ic_dialog_info,
				getString(R.string.positive),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 默认关闭界面
						defaultFinish();
					}
				}, getString(R.string.negative),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
	}

	public class IntentSpan extends ClickableSpan {
		private final OnClickListener listener;

		public IntentSpan(View.OnClickListener listener) {
			this.listener = listener;
		}

		public void onClick(View view) {
			listener.onClick(view);
		}

		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			ds.setUnderlineText(true);
		}
	}
}

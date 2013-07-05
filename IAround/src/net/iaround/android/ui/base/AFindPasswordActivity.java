package net.iaround.android.ui.base;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import net.iaround.android.BaseActivity;

/**
 * @fileName AFindPasswordActivity.java
 * @package net.iaround.android.ui.base
 * @description 找回密码界面父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class AFindPasswordActivity extends BaseActivity implements
		OnClickListener, TextWatcher {
	/** 返回文本 **/
	protected TextView mTvBack;
	/** 邮箱输入框 **/
	protected AutoCompleteTextView mActvEmail;
	/** 重置按钮 **/
	protected Button mBtnReset;
	/** 邮箱提示后缀 **/
	private static final String[] EMAILS_SUFFIX = { "@163.com", "@qq.com",
			"@gmail.com", "@126.com", "@yahoo.com", "@sina.com", "@sohu.com",
			"@hotmail.com", "@vip.sina.com", "@3g.sina.com", "@21cn.com",
			"@tom.com", "@139.com" };

	/** 添加自动提示 **/
	protected void addAutoComplete(String s) {
		if (!TextUtils.isEmpty(s)) {
			String[] arrays = getAutoComplete(s);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, arrays);
			mActvEmail.setAdapter(adapter);
		}
	}

	/** 获取自动提示的数据 **/
	private String[] getAutoComplete(String s) {
		String[] arrays = new String[EMAILS_SUFFIX.length];
		for (int i = 0; i < EMAILS_SUFFIX.length; i++) {
			arrays[i] = s + EMAILS_SUFFIX[i];
		}
		return arrays;
	}
}

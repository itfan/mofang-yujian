package net.iaround.android.ui.base;

import net.iaround.android.BaseActivity;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @fileName AAboutActivity.java
 * @package net.iaround.android.ui.base
 * @description 关于界面父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class AAbountActivity extends BaseActivity implements
		OnClickListener, OnBackClickListener {
	/** 自定义标题栏 **/
	protected IARActionBar mIarActionBar;
	/** 新版本特性按钮 **/
	protected Button mBtnNewFeatures;
	/** 访问官方网站按钮 **/
	protected Button mBtnVisitAppWebsite;
	/** 访问官网微博按钮 **/
	protected Button mBtnVisitAppWeiBo;
	/** 客服电话按钮 **/
	protected Button mBtnCustomerServiceHotline;
	/** 客服邮箱按钮 **/
	protected Button mBtnCustomerServiceMail;

	/** 打开官网网站 **/
	protected void openWebsite() {
		openUrl("http://www.iaround.com/m/android.html");
	}

	/** 打开官网微博对话框 **/
	protected void openWeiBo() {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.visit_app_weibo))
				.setItems(R.array.weibo_name,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									// 新浪微博地址
									openUrl("http://e.weibo.com/311000");
									break;

								case 1:
									// 腾讯微博地址
									openUrl("http://t.qq.com/iAround");
									break;

								case 2:
									//暂无请求地址
									showShortToast(R.string.no_have_url);
									break;

								case 3:
									// 暂无请求地址
									showShortToast(R.string.no_have_url);
									break;
								}
							}
						}).create().show();
	}

	/** 根据URL打开界面 **/
	protected void openUrl(String url) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		startActivity(intent);

	}

	/** 打客服电话 **/
	protected void call() {
		Intent callIntent = new Intent(Intent.ACTION_DIAL,
				Uri.parse("tel:00862085618349"));
		startActivity(callIntent);
	}

	/** 发送邮件到客服邮箱 **/
	protected void sendTo() {
		Intent data = new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:cs@iaround.com"));
		startActivity(data);
	}

}

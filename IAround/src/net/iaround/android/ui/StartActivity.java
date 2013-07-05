package net.iaround.android.ui;

import net.iaround.android.R;
import net.iaround.android.ui.base.AStartActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @fileName StartActivity.java
 * @package net.iaround.android.ui
 * @description 欢迎界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */

public class StartActivity extends AStartActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// 暂时不做任何操作
	}

	@Override
	protected void setListener() {
		// 暂时不做任何操作
	}

	@Override
	protected void init() {
		// 延迟1500毫秒发送消息到消息队列
		handler.sendEmptyMessageDelayed(0, 1500);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 跳转到登录界面,并默认方式关闭当前界面
			startActivity(LoginActivity.class);
			defaultFinish();
		}
	};
}

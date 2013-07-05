package net.iaround.android.ui.contact;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ContactChatFragment extends ContactChatFragmentBase {

	public ContactChatFragment(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.contact_chat, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mDisplay = (ListView) findViewById(R.id.contact_chat_display);
		mBottom = (LinearLayout) findViewById(R.id.contact_chat_bottom);
		mAllRead = (TextView) findViewById(R.id.contact_chat_all_read);
		mDelAll = (TextView) findViewById(R.id.contact_chat_del_all);
	}

	@Override
	protected void setListener() {
		mAllRead.setOnClickListener(this);
		mDelAll.setOnClickListener(this);
	}

	@Override
	protected void init() {
		getChat();
		mAdapter = new Adapter();
		mDisplay.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.contact_chat_all_read:
			showShortToast(getString(R.string.friend_near_contact_all_read));
			dismissBottom();
			break;

		case R.id.contact_chat_del_all:
			showShortToast(getString(R.string.friend_near_contact_del_all));
			dismissBottom();
			break;
		}
	}
}

package net.iaround.android;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.iaround.android.ui.contact.ContactChat;
import net.iaround.android.ui.contact.ContactFriend;
import net.iaround.android.ui.focus.Focus;
import net.iaround.android.ui.meetgame.MeetGame;
import net.iaround.android.ui.messagecenter.MessageCenter;
import net.iaround.android.ui.nearby.NearBy;
import net.iaround.android.ui.vip.VipPrivilege;
import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

public class BaseApplication extends Application {
	public List<NearBy> mNearBy;
	public Map<String, SoftReference<Bitmap>> mNearByCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<MeetGame> mMeetGame;
	public Map<String, SoftReference<Bitmap>> mMeetGameCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<Focus> mFocus;
	public Map<String, SoftReference<Bitmap>> mFocusCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<VipPrivilege> mVipPrivileges;
	public Map<String, SoftReference<Bitmap>> mVipPrivilegeCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<MessageCenter> mMessageCenters;
	public Map<String, SoftReference<Bitmap>> mMessageCenterCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<ContactChat> mContactChats;
	public Map<String, SoftReference<Bitmap>> mContactChatCache = new HashMap<String, SoftReference<Bitmap>>();
	public List<ContactFriend> mContactFriends;
	public Map<String, SoftReference<Bitmap>> mContactFriendCache = new HashMap<String, SoftReference<Bitmap>>();

	public List<Fragment> mGetGoldCoinsFragments;
	public long mLoginTime;

	@Override
	public void onCreate() {
		super.onCreate();
		mLoginTime = System.currentTimeMillis();
	}
}

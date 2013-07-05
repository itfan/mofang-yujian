package net.iaround.android.ui.contact;

import java.util.ArrayList;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public abstract class ContactFriendFragmentBase extends BaseFragment implements
		OnClickListener, OnFocusChangeListener {

	protected EditText mEnter;
	protected Button mClear;
	protected ListView mDisplay;
	protected View mShadow;
	protected Adapter mAdapter;

	public ContactFriendFragmentBase(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	protected void getFriend() {
		if (mApplication.mContactFriends == null) {
			mApplication.mContactFriends = new ArrayList<ContactFriend>();
		}
		if (mApplication.mContactFriends.isEmpty()) {
			String json = TextUtil.getJosn(mContext,
					TextUtil.CONTACT_FRIEND_PATH);
			try {
				JSONArray array = new JSONArray(json);
				ContactFriend contactFriend = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					contactFriend = new ContactFriend();
					contactFriend.setName(object.getString("name"));
					contactFriend.setAvatar(object.getString("avatar"));
					contactFriend.setAge(object.getString("age"));
					contactFriend.setGender(object.getInt("gender"));
					contactFriend.setWeibo(object.getBoolean("weibo"));
					contactFriend.setOnline(object.getBoolean("online"));
					contactFriend.setSignature(object.getString("signature"));
					mApplication.mContactFriends.add(contactFriend);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	protected class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mContactFriends == null) {
				return 0;
			} else {
				return mApplication.mContactFriends.size();
			}
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mContactFriends.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.contact_friend_item, null);
				holder = new ViewHolder();
				holder.avatar = (ImageView) convertView
						.findViewById(R.id.contact_friend_item_avatar);
				holder.name = (TextView) convertView
						.findViewById(R.id.contact_friend_item_name);
				holder.ageLayout = (LinearLayout) convertView
						.findViewById(R.id.contact_friend_item_age_layout);
				holder.age = (TextView) convertView
						.findViewById(R.id.contact_friend_item_age);
				holder.gender = (ImageView) convertView
						.findViewById(R.id.contact_friend_item_gender);
				holder.weibo = (ImageView) convertView
						.findViewById(R.id.contact_friend_item_weibo);
				holder.online = (TextView) convertView
						.findViewById(R.id.contact_friend_item_online);
				holder.signature = (TextView) convertView
						.findViewById(R.id.contact_friend_item_signature);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ContactFriend contactFriend = mApplication.mContactFriends
					.get(position);
			holder.avatar.setImageBitmap(ImageUtil.getCache(mContext,
					mApplication.mContactFriendCache, ImageUtil.CONTACT_PATH,
					contactFriend.getAvatar()));
			holder.name.setText(contactFriend.getName());
			holder.ageLayout.setBackgroundResource(ImageUtil
					.getAgeBackground(contactFriend.getGender()));
			holder.age.setText(contactFriend.getAge());
			holder.gender.setImageBitmap(ImageUtil.getNearBySex(getResources(),
					contactFriend.getGender()));
			if (contactFriend.isWeibo()) {
				holder.weibo.setVisibility(View.VISIBLE);
			} else {
				holder.weibo.setVisibility(View.GONE);
			}
			if (contactFriend.isOnline()) {
				holder.online.setText(getString(R.string.current_online));
			} else {
				holder.online.setText(getString(R.string.current_offline));
			}
			holder.signature.setText(contactFriend.getSignature());
			return convertView;
		}

		class ViewHolder {
			ImageView avatar;
			TextView name;
			LinearLayout ageLayout;
			TextView age;
			ImageView gender;
			ImageView weibo;
			TextView online;
			TextView signature;
		}
	}
}

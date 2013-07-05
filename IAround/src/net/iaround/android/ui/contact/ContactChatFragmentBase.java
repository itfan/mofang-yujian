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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public abstract class ContactChatFragmentBase extends BaseFragment implements
		OnClickListener {

	protected ListView mDisplay;
	protected LinearLayout mBottom;
	protected TextView mAllRead;
	protected TextView mDelAll;

	protected Adapter mAdapter;

	public ContactChatFragmentBase(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	protected void getChat() {
		if (mApplication.mContactChats == null) {
			mApplication.mContactChats = new ArrayList<ContactChat>();
		}
		if (mApplication.mContactChats.isEmpty()) {
			String json = TextUtil
					.getJosn(mContext, TextUtil.CONTACT_CHAT_PATH);
			try {
				JSONArray array = new JSONArray(json);
				ContactChat contactChat = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					contactChat = new ContactChat();
					contactChat.setName(object.getString("name"));
					contactChat.setAvatar(object.getString("avatar"));
					contactChat.setAge(object.getString("age"));
					contactChat.setGender(object.getInt("gender"));
					contactChat.setDistance(object.getString("distance"));
					contactChat.setTime(object.getString("time"));
					contactChat.setContent_type(object.getInt("content_type"));
					contactChat.setContent(object.getString("content"));
					mApplication.mContactChats.add(contactChat);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void showBottom() {
		mBottom.setVisibility(View.VISIBLE);
	}

	public void dismissBottom() {
		mBottom.setVisibility(View.GONE);
	}

	public boolean bottomIsShowing() {
		return mBottom.isShown();
	}

	protected class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mContactChats == null) {
				return 0;
			} else {
				return mApplication.mContactChats.size();
			}
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mContactChats.get(position);
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
						R.layout.contact_chat_item, null);
				holder = new ViewHolder();
				holder.avatar = (ImageView) convertView
						.findViewById(R.id.contact_chat_item_avatar);
				holder.name = (TextView) convertView
						.findViewById(R.id.contact_chat_item_name);
				holder.ageLayout = (LinearLayout) convertView
						.findViewById(R.id.contact_chat_item_age_layout);
				holder.age = (TextView) convertView
						.findViewById(R.id.contact_chat_item_age);
				holder.gender = (ImageView) convertView
						.findViewById(R.id.contact_chat_item_gender);
				holder.distance = (TextView) convertView
						.findViewById(R.id.contact_chat_item_distance);
				holder.time = (TextView) convertView
						.findViewById(R.id.contact_chat_item_time);
				holder.friendContent = (TextView) convertView
						.findViewById(R.id.contact_chat_item_friend_content);
				holder.myselfContent = (TextView) convertView
						.findViewById(R.id.contact_chat_item_myself_content);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ContactChat contactChat = mApplication.mContactChats.get(position);
			holder.avatar.setImageBitmap(ImageUtil.getCache(mContext,
					mApplication.mContactChatCache, ImageUtil.CONTACT_PATH,
					contactChat.getAvatar()));
			holder.name.setText(contactChat.getName());
			holder.ageLayout.setBackgroundResource(ImageUtil
					.getAgeBackground(contactChat.getGender()));
			holder.age.setText(contactChat.getAge());
			holder.gender.setImageBitmap(ImageUtil.getNearBySex(getResources(),
					contactChat.getGender()));
			holder.distance.setText(contactChat.getDistance());
			holder.time.setText(contactChat.getTime());
			if (contactChat.getContent_type() == 0) {
				holder.friendContent.setVisibility(View.VISIBLE);
				holder.myselfContent.setVisibility(View.GONE);
				holder.friendContent.setText(contactChat.getContent());
			} else {
				holder.myselfContent.setVisibility(View.VISIBLE);
				holder.friendContent.setVisibility(View.GONE);
				holder.myselfContent.setText(contactChat.getContent());
			}
			return convertView;
		}

		class ViewHolder {
			ImageView avatar;
			TextView name;
			LinearLayout ageLayout;
			TextView age;
			ImageView gender;
			TextView distance;
			TextView time;
			TextView friendContent;
			TextView myselfContent;
		}
	}
}

package net.iaround.android.ui.messagecenter;

import java.util.ArrayList;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public abstract class MessageCenterFragmentBase extends BaseFragment implements
		OnBackClickListener {

	protected IARActionBar mIarActionBar;
	protected ListView mDisplay;
	protected Adapter mAdapter;
	public MessageCenterFragmentBase(BaseApplication application,
			Activity activity, Context context) {
		super(application, activity, context);
	}

	protected void getMessageCenter() {
		if (mApplication.mMessageCenters == null) {
			mApplication.mMessageCenters = new ArrayList<MessageCenter>();
		}
		if (mApplication.mMessageCenters.isEmpty()) {
			String json = TextUtil.getJosn(mContext,
					TextUtil.MESSAGECENTER_PATH);
			try {
				JSONArray array = new JSONArray(json);
				MessageCenter messageCenter = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					messageCenter = new MessageCenter();
					messageCenter.setAvatar(object.getString("avatar"));
					messageCenter.setName(object.getString("name"));
					messageCenter.setTime(object.getString("time"));
					mApplication.mMessageCenters.add(messageCenter);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	protected class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mMessageCenters == null) {
				return 0;
			}
			return mApplication.mMessageCenters.size();
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mMessageCenters.get(position);
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
						R.layout.messagecenter_item, null);
				holder = new ViewHolder();
				holder.avatar = (ImageView) convertView
						.findViewById(R.id.avatar);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.time = (TextView) convertView.findViewById(R.id.time);
				holder.description = (TextView) convertView
						.findViewById(R.id.description);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			MessageCenter messageCenter = mApplication.mMessageCenters
					.get(position);
			holder.avatar.setImageBitmap(ImageUtil.getCache(mContext,
					mApplication.mMessageCenterCache,
					ImageUtil.MESSAGECENTER_PATH, messageCenter.getAvatar()));
			holder.name.setText(messageCenter.getName());
			holder.time.setText(messageCenter.getTime());
			holder.description.setText(getString(R.string.dynamic_whosawme));
			return convertView;
		}

		class ViewHolder {
			ImageView avatar;
			TextView name;
			TextView time;
			TextView description;
		}
	}
}

package net.iaround.android.ui.contact;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.ui.common.OnMenuClickListener;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class ContactFragmentBase extends BaseFragment implements
		OnBackClickListener, OnMenuClickListener {

	protected IARActionBar mIarActionBar;

	protected ContactChatFragment mContactChatFragment;
	protected ContactFriendFragment mContactFriendFragment;
	protected ContactFollowingFragment mContactFollowingFragment;
	protected ContactFansFragment mContactFansFragment;
	protected ContactBlackListFragment mContactBlackListFragment;
	protected TitlePopupwindowAdapter mTitlePopupwindowAdapter;
	protected int mChooseId = 0;

	public ContactFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	protected void initContent() {
		mContactChatFragment = new ContactChatFragment(mApplication, mActivity,
				mContext);
		FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.contact_content, mContactChatFragment);
		transaction.commit();
	}

	protected void changeContent(Fragment fragment) {
		FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.contact_content, fragment);
		transaction.commit();
	}

	protected class TitlePopupwindowAdapter extends BaseAdapter {
		private String[] items = { getString(R.string.chat_near_contact),
				getString(R.string.friend), getString(R.string.following),
				getString(R.string.fans), getString(R.string.black_list) };
		private int[] icons = { R.drawable.side_bar_icon_current_oline,
				R.drawable.side_bar_icon_chat_room_gray,
				R.drawable.friend_near_chat_edit,
				R.drawable.side_bar_icon_wink_bomb_gray,
				R.drawable.side_bar_icon_blacklist };

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.title_popupwindow_item, null);
				holder = new ViewHolder();
				holder.mLayout = (LinearLayout) convertView
						.findViewById(R.id.title_pop_layout);
				holder.mIcon = (ImageView) convertView
						.findViewById(R.id.title_pop_icon);
				holder.mText = (TextView) convertView
						.findViewById(R.id.title_pop_text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (mChooseId == position) {
				holder.mLayout.setBackgroundColor(getResources().getColor(
						R.color.c_ff000000));
			} else {
				holder.mLayout.setBackgroundResource(R.drawable.title_pop_bg);
			}
			holder.mIcon.setImageResource(icons[position]);
			holder.mText.setText(items[position]);
			holder.mLayout.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					mChooseId = position;
					notifyDataSetChanged();
					mIarActionBar.dismissTitlePopupwindow();
					mIarActionBar.setIcon(icons[position]);
					mIarActionBar.setTitle(items[position]);
					switch (mChooseId) {
					case 0:
						mIarActionBar.showMenu();
						mContactChatFragment = new ContactChatFragment(
								mApplication, mActivity, mContext);
						changeContent(mContactChatFragment);
						break;

					case 1:
						mIarActionBar.dismissMenu();
						mContactFriendFragment = new ContactFriendFragment(
								mApplication, mActivity, mContext);
						changeContent(mContactFriendFragment);
						break;

					case 2:
						mIarActionBar.dismissMenu();
						mContactFollowingFragment = new ContactFollowingFragment(
								mApplication, mActivity, mContext);
						changeContent(mContactFollowingFragment);
						break;

					case 3:
						mIarActionBar.dismissMenu();
						mContactFansFragment = new ContactFansFragment(
								mApplication, mActivity, mContext);
						changeContent(mContactFansFragment);
						break;

					case 4:
						mIarActionBar.dismissMenu();
						mContactBlackListFragment = new ContactBlackListFragment(
								mApplication, mActivity, mContext);
						changeContent(mContactBlackListFragment);
						break;
					}
				}
			});
			return convertView;
		}

		class ViewHolder {
			LinearLayout mLayout;
			ImageView mIcon;
			TextView mText;
		}
	}
}

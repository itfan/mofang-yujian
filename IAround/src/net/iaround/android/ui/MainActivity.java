package net.iaround.android.ui;

import java.util.ArrayList;
import java.util.List;

import net.iaround.android.BaseApplication;
import net.iaround.android.R;
import net.iaround.android.sliding.BaseSlidingFragmentActivity;
import net.iaround.android.sliding.SlidingMenu;
import net.iaround.android.sliding.SlidingMenu.OnOpenListener;
import net.iaround.android.ui.contact.ContactFragment;
import net.iaround.android.ui.focus.FocusFragment;
import net.iaround.android.ui.meetgame.MeetGameFragment;
import net.iaround.android.ui.messagecenter.MessageCenterFragment;
import net.iaround.android.ui.nearby.NearByFragment;
import net.iaround.android.ui.search.SearchFragment;
import net.iaround.android.ui.space.SpaceFragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @fileName MainActivity.java
 * @package net.iaround.android.ui
 * @description 菜单主界面
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class MainActivity extends BaseSlidingFragmentActivity implements
		OnOpenListener {
	/** 菜单 **/
	private SlidingMenu mSlidingMenu;
	/** 菜单头界面 **/
	private View mVMenuHead;
	/** 菜单列表 **/
	private ExpandableListView mElvMenu;
	/** Appliction基类对象 **/
	private BaseApplication mApplication;
	/** 我的资料 **/
	private SpaceFragment mSpaceFragment;
	/** 邂逅游戏 **/
	private MeetGameFragment mMeetGameFragment;
	/** 全球焦点 **/
	private FocusFragment mFocusFragment;
	/** 附近用户 **/
	private NearByFragment mNearByFragment;
	/** 高级搜索 **/
	private SearchFragment mSearchFragment;
	/** 联系人 **/
	private ContactFragment mContactFragment;
	/** 最新动态 **/
	private MessageCenterFragment mMessageCenterFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取BaseApplication对象
		mApplication = (BaseApplication) getApplication();
		setBehindContentView(R.layout.slidingmenu_menu);
		initSlidingMenu();
		setContentView(R.layout.slidingmenu_content);
		mVMenuHead = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.slidingmenu_menu_head, null);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mElvMenu = (ExpandableListView) findViewById(R.id.menu);
	}

	private void setListener() {
		mSlidingMenu.setOnOpenListener(this);
		mVMenuHead.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mSpaceFragment = new SpaceFragment(mApplication,
						MainActivity.this, MainActivity.this);
				changeContent(mSpaceFragment);
			}
		});
		mElvMenu.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {
				return true;
			}
		});
		mElvMenu.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				switch (groupPosition) {
				case 0:
					switch (childPosition) {
					case 0:
						mMeetGameFragment = new MeetGameFragment(mApplication,
								MainActivity.this, MainActivity.this);
						changeContent(mMeetGameFragment);
						break;

					case 1:
						mFocusFragment = new FocusFragment(mApplication,
								MainActivity.this, MainActivity.this);
						changeContent(mFocusFragment);
						break;

					case 2:
						mNearByFragment = new NearByFragment(mApplication,
								MainActivity.this, MainActivity.this);
						changeContent(mNearByFragment);
						break;

					case 3:
						mSearchFragment = new SearchFragment(mApplication,
								MainActivity.this, MainActivity.this);
						changeContent(mSearchFragment);
						break;
					}
					break;

				case 1:
					switch (childPosition) {
					case 0:
						mContactFragment = new ContactFragment(mApplication,
								MainActivity.this, MainActivity.this);
						changeContent(mContactFragment);
						break;

					case 1:
						mMessageCenterFragment = new MessageCenterFragment(
								mApplication, MainActivity.this,
								MainActivity.this);
						changeContent(mMessageCenterFragment);
						break;
					}
					break;

				case 2:

					break;

				case 3:
					switch (childPosition) {
					case 0:

						break;

					case 1:
						new AlertDialog.Builder(MainActivity.this)
								.setTitle(R.string.dialog_title)
								.setMessage(R.string.dialog_exit)
								.setPositiveButton(
										getString(R.string.exit_iaround),
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												defaultFinish();
												Process.killProcess(Process
														.myPid());
												System.exit(0);
											}
										})
								.setNegativeButton(getString(R.string.cancel),
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.cancel();
											}
										}).create().show();

						break;
					}
					break;
				}
				return false;
			}
		});
	}

	private void init() {
		initContent();
		initMenu();
	}

	/** 初始化菜单 **/
	private void initSlidingMenu() {
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setShadowDrawable(R.drawable.sidebar_right_shadow);
	}

	/** 初始化显示内容 **/
	private void initContent() {
		mNearByFragment = new NearByFragment(mApplication, this, this);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.content, mNearByFragment);
		transaction.commit();
	}

	/** 修改显示的内容 **/
	private void changeContent(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.content, fragment);
		transaction.commit();
		showContent();
	}

	/** 初始化菜单列表内容 **/
	private void initMenu() {
		String[] mGroups = getResources().getStringArray(
				R.array.menu_group_list);
		String[] mChilds = getResources().getStringArray(
				R.array.menu_child_list);
		int[] mIcons = new int[] { R.drawable.side_bar_icon_wink_bomb,
				R.drawable.sb_i_am_the_focus,
				R.drawable.side_bar_icon_map_mode,
				R.drawable.side_bar_icon_advanced_search,
				R.drawable.side_bar_icon_my_friend,
				R.drawable.side_bar_icon_look_me,
				R.drawable.side_bar_icon_chat_room,
				R.drawable.side_bar_icon_hot_photo, R.drawable.sys_set_set,
				R.drawable.side_bar_icon_quit };
		List<List<Bundle>> mChildsList = new ArrayList<List<Bundle>>();
		for (int i = 0; i < mGroups.length; i++) {
			List<Bundle> list = new ArrayList<Bundle>();
			switch (i) {
			case 0:
				for (int j = 0; j < 4; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 1:
				for (int j = 4; j < 6; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 2:
				for (int j = 6; j < 8; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 3:
				for (int j = 8; j < 10; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;
			}
			mChildsList.add(list);
		}
		mElvMenu.addHeaderView(mVMenuHead);
		MenuAdapter adapter = new MenuAdapter(mGroups, mChildsList);
		mElvMenu.setAdapter(adapter);
		for (int i = 0; i < mGroups.length; i++) {
			mElvMenu.expandGroup(i);
		}
	}

	private class MenuAdapter extends BaseExpandableListAdapter {
		private String[] mGroups;
		private List<List<Bundle>> mChilds;

		public MenuAdapter(String[] groups, List<List<Bundle>> childs) {
			mGroups = groups;
			mChilds = childs;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return mChilds.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.slidingmenu_menu_child_item, null);
				holder = new ChildViewHolder();
				holder.mIcon = (ImageView) convertView
						.findViewById(R.id.menu_child_icon);
				holder.mName = (TextView) convertView
						.findViewById(R.id.menu_child_name);
				convertView.setTag(holder);
			} else {
				holder = (ChildViewHolder) convertView.getTag();
			}
			Bundle bundle = mChilds.get(groupPosition).get(childPosition);
			holder.mIcon.setImageResource(bundle.getInt("icon"));
			holder.mName.setText(bundle.getString("name"));
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return mChilds.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return mGroups[groupPosition];
		}

		@Override
		public int getGroupCount() {
			return mGroups.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.slidingmenu_menu_group_item, null);
				holder = new GroupViewHolder();
				holder.mName = (TextView) convertView
						.findViewById(R.id.menu_group_name);
				convertView.setTag(holder);
			} else {
				holder = (GroupViewHolder) convertView.getTag();
			}
			holder.mName.setText(mGroups[groupPosition]);
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		class GroupViewHolder {
			TextView mName;
		}

		class ChildViewHolder {
			ImageView mIcon;
			TextView mName;
		}
	}

	@Override
	public void onOpen() {
		if (mNearByFragment != null) {
			mNearByFragment.slidingMenuOpen();
		}
	}

	/** 显示菜单列表 **/
	public void showMenu() {
		mSlidingMenu.showMenu();
	}

	/** 显示内容 **/
	public void showContent() {
		mSlidingMenu.showContent();
	}
}

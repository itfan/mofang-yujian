package net.iaround.android.ui.nearby;

import java.util.ArrayList;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.IARGridView;
import net.iaround.android.ui.common.IARListView;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public abstract class NearByFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener {
	protected IARActionBar mIarActionBar;
	protected HorizontalScrollView mScrollView;
	protected LinearLayout mFocus;
	protected IARListView mIarListView;
	protected IARGridView mIarGridView;
	protected TitlePopupwindowAdapter mTitlePopupwindowAdapter;

	protected boolean mIsSlide = false;
	protected boolean mIsShow = true;
	protected int mChooseId = 0;

	public NearByFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	protected Animation getLeftInAnimation(ViewGroup left) {
		TranslateAnimation translate = new TranslateAnimation(-left.getWidth(),
				0f, 0f, 0f);
		translate.setFillAfter(true);
		translate.setDuration(500);
		return translate;
	}

	protected Animation getLeftOutAnimation(ViewGroup left) {
		TranslateAnimation translate = new TranslateAnimation(0f,
				-left.getWidth(), 0f, 0f);
		translate.setFillAfter(true);
		translate.setDuration(500);
		return translate;
	}

	protected void initNearBy() {
		if (mApplication.mNearBy == null) {
			mApplication.mNearBy = new ArrayList<NearBy>();
		}
		if (mApplication.mNearBy.isEmpty()) {
			getNearBy();
		}
		mIarListView.setAdapter(new ListAdapter());
		mIarGridView.setAdapter(new GridAdapter());
	}

	protected void getNearBy() {
		String json = TextUtil.getJosn(mContext, TextUtil.NEARBY_PATH);
		try {
			JSONArray array = new JSONArray(json);
			NearBy nearBy = null;
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				nearBy = new NearBy();
				nearBy.setAvatar(object.getString("avatar"));
				nearBy.setName(object.getString("name"));
				nearBy.setAge(object.getString("age"));
				nearBy.setSex(object.getInt("sex"));
				nearBy.setDistance(object.getString("distance"));
				nearBy.setTime(object.getString("time"));
				nearBy.setWeibo(object.getBoolean("weibo"));
				nearBy.setSignature(object.getString("signature"));
				mApplication.mNearBy.add(nearBy);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected class ListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mNearBy == null) {
				return 0;
			}
			return mApplication.mNearBy.size();
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mNearBy.get(position);
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
						R.layout.nearby_list_item, null);
				holder = new ViewHolder();
				holder.mAvatar = (ImageView) convertView
						.findViewById(R.id.nearby_list_item_avatar);
				holder.mName = (TextView) convertView
						.findViewById(R.id.nearby_list_item_name);
				holder.mWeiBo = (ImageView) convertView
						.findViewById(R.id.nearby_list_item_weibo);
				holder.mAgeLayout = (LinearLayout) convertView
						.findViewById(R.id.nearby_list_item_age_layout);
				holder.mAge = (TextView) convertView
						.findViewById(R.id.nearby_list_item_age);
				holder.mSex = (ImageView) convertView
						.findViewById(R.id.nearby_list_item_sex);
				holder.mDistance = (TextView) convertView
						.findViewById(R.id.nearby_list_item_distance);
				holder.mTime = (TextView) convertView
						.findViewById(R.id.nearby_list_item_time);
				holder.mSignature = (TextView) convertView
						.findViewById(R.id.nearby_list_item_signature);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NearBy nearBy = (NearBy) getItem(position);
			holder.mAvatar.setImageBitmap(ImageUtil.getCache(mContext,
					mApplication.mNearByCache, ImageUtil.NEARBY_PATH,
					nearBy.getAvatar()));
			holder.mName.setText(nearBy.getName());
			if (nearBy.isWeibo()) {
				holder.mWeiBo.setVisibility(View.VISIBLE);
				holder.mWeiBo.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.weibo_sina));
			} else {
				holder.mWeiBo.setVisibility(View.INVISIBLE);
			}
			holder.mAgeLayout.setBackgroundResource(ImageUtil
					.getAgeBackground(nearBy.getSex()));
			holder.mAge.setText(nearBy.getAge());
			holder.mSex.setImageBitmap(ImageUtil.getNearBySex(getResources(),
					nearBy.getSex()));
			holder.mDistance.setText(nearBy.getDistance());
			holder.mTime.setText(nearBy.getTime());
			holder.mSignature.setText(nearBy.getSignature());
			return convertView;
		}

		class ViewHolder {
			ImageView mAvatar;
			TextView mName;
			ImageView mWeiBo;
			LinearLayout mAgeLayout;
			TextView mAge;
			ImageView mSex;
			TextView mDistance;
			TextView mTime;
			TextView mSignature;
		}
	}

	protected class GridAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (mApplication.mNearBy == null) {
				return 0;
			}
			return mApplication.mNearBy.size();
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mNearBy.get(position);
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
						R.layout.nearby_grid_item, null);
				holder = new ViewHolder();
				holder.mAvatar = (ImageView) convertView
						.findViewById(R.id.nearby_grid_item_avatar);
				int padding = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 40, mContext
								.getResources().getDisplayMetrics());
				LayoutParams params = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				DisplayMetrics metric = new DisplayMetrics();
				mActivity.getWindowManager().getDefaultDisplay()
						.getMetrics(metric);
				params.width = (metric.widthPixels - padding) / 4;
				params.height = (metric.widthPixels - padding) / 4;
				holder.mAvatar.setLayoutParams(params);
				holder.mDistance = (TextView) convertView
						.findViewById(R.id.nearby_grid_item_distance);
				holder.mSex = (ImageView) convertView
						.findViewById(R.id.nearby_grid_item_sex);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NearBy nearBy = (NearBy) getItem(position);
			holder.mAvatar.setImageBitmap(ImageUtil.getCache(mContext,
					mApplication.mNearByCache, ImageUtil.NEARBY_PATH,
					nearBy.getAvatar()));
			holder.mDistance.setText(nearBy.getDistance());
			holder.mSex.setImageBitmap(ImageUtil.getNearBySmallSex(
					getResources(), nearBy.getSex()));
			return convertView;
		}

		class ViewHolder {
			ImageView mAvatar;
			TextView mDistance;
			ImageView mSex;
		}
	}

	protected class TitlePopupwindowAdapter extends BaseAdapter {
		private String[] items = { "列表模式", "宫格模式", "地图模式" };
		private int[] icons = { R.drawable.nearby_list, R.drawable.neayby_grid,
				R.drawable.nearby_map };

		@Override
		public int getCount() {
			return 3;
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
					switch (mChooseId) {
					case 0:
						mIarListView.setVisibility(View.VISIBLE);
						mIarGridView.setVisibility(View.GONE);
						mIarActionBar.setTitle2("(列表)");
						break;
					case 1:
						mIarListView.setVisibility(View.GONE);
						mIarGridView.setVisibility(View.VISIBLE);
						mIarActionBar.setTitle2("(宫格)");
						break;
					case 2:
						mChooseId = 0;
						mIarListView.setVisibility(View.VISIBLE);
						mIarGridView.setVisibility(View.GONE);
						mIarActionBar.setTitle2("(列表)");
						Toast.makeText(mContext, "暂不支持地图模式", Toast.LENGTH_SHORT)
								.show();
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

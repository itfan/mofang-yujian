package net.iaround.android.ui.focus;

import java.util.ArrayList;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.IARFocusImageView;
import net.iaround.android.ui.common.IARFocusTitle;
import net.iaround.android.ui.common.IARGridView;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;

public abstract class FocusFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener {
	protected IARActionBar mIarActionBar;
	protected IARFocusTitle mIarFocusTitle;
	protected IARGridView mIarGridView;

	public FocusFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	protected void getFocus() {
		if (mApplication.mFocus == null) {
			mApplication.mFocus = new ArrayList<Focus>();
		}
		if (mApplication.mFocus.isEmpty()) {
			String json = TextUtil.getJosn(mContext, TextUtil.FOCUS_PATH);
			try {
				JSONArray array = new JSONArray(json);
				Focus focus = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					focus = new Focus();
					focus.setImage(object.getString("image"));
					focus.setTime(object.getString("time"));
					mApplication.mFocus.add(focus);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	protected class GridAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mFocus == null) {
				return 0;
			}
			return mApplication.mFocus.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mApplication.mFocus.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			IARFocusImageView imageView = null;
			if (convertView == null) {
				imageView = new IARFocusImageView(mContext);
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
				imageView.setLayoutParams(params);
				convertView = imageView;
			} else {
				imageView = (IARFocusImageView) convertView;
			}
			Focus focus = mApplication.mFocus.get(position);
			imageView.setImageBitmap(ImageUtil.toRoundCorner(ImageUtil
					.getCache(mContext, mApplication.mFocusCache,
							ImageUtil.FOCUS_PATH, focus.getImage()), 15));
			imageView.setText(focus.getTime());
			imageView.setMargin(0);
			imageView.setBackgroundColor(Color.TRANSPARENT);
			imageView.setVisibility(View.GONE);
			return imageView;
		}

	}
}

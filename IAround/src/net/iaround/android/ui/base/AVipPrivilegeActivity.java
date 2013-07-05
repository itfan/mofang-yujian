package net.iaround.android.ui.base;

import java.util.ArrayList;

import net.iaround.android.BaseActivity;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.ui.vip.VipPrivilege;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @fileName AVipPrivilegeActivity.java
 * @package net.iaround.android.ui.base
 * @description VIP特权父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class AVipPrivilegeActivity extends BaseActivity implements
		OnClickListener, OnBackClickListener {
	/** 自定义标题栏 **/
	protected IARActionBar mIarActionBar;
	/** 内容显示 **/
	protected ListView mDisplay;
	/** 获取金币按钮 **/
	protected Button mGetGold;
	/** 内容头布局 **/
	protected View mHead;
	/** 头布局头像 **/
	protected ImageView mHeadAvatar;
	/** 头布局标题1 **/
	protected TextView mHeadTitle1;
	/** 头布局标题2 **/
	protected TextView mHeadTitle2;
	/** 内容适配器 **/
	protected Adapter mAdapter;

	public View findViewById(int id) {
		if (mHead != null) {
			if (mHead.findViewById(id) != null) {
				return mHead.findViewById(id);
			}
		}
		return super.findViewById(id);
	}

	/** 获取带有HTML格式的文本 **/
	protected Spanned getStringFromRes(int resId) {
		return Html.fromHtml(getString(resId));
	}

	/** 获取VIP特权数据 **/
	protected void getVipPrivilege() {
		if (mApplication.mVipPrivileges == null) {
			mApplication.mVipPrivileges = new ArrayList<VipPrivilege>();
		}
		if (mApplication.mVipPrivileges.isEmpty()) {
			String json = TextUtil.getJosn(AVipPrivilegeActivity.this,
					TextUtil.VIP_PRIVILEGE_PATH);
			try {
				JSONArray array = new JSONArray(json);
				VipPrivilege vipPrivilege = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					vipPrivilege = new VipPrivilege();
					vipPrivilege.setIcon(object.getString("icon"));
					vipPrivilege.setTitle(object.getString("title"));
					vipPrivilege
							.setDescription(object.getString("description"));
					mApplication.mVipPrivileges.add(vipPrivilege);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mApplication.mVipPrivileges == null) {
				return 0;
			}
			return mApplication.mVipPrivileges.size();
		}

		@Override
		public Object getItem(int position) {
			return mApplication.mVipPrivileges.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(AVipPrivilegeActivity.this)
						.inflate(R.layout.vipprivilege_acitivty_item, null);
				holder = new ViewHolder();
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.description = (TextView) convertView
						.findViewById(R.id.description);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			VipPrivilege vipPrivilege = mApplication.mVipPrivileges
					.get(position);
			holder.icon.setImageBitmap(ImageUtil.getCache(
					AVipPrivilegeActivity.this,
					mApplication.mVipPrivilegeCache, ImageUtil.VIP_PATH,
					vipPrivilege.getIcon()));
			holder.title.setText(vipPrivilege.getTitle());
			holder.description.setText(vipPrivilege.getDescription());
			return convertView;
		}

		class ViewHolder {
			ImageView icon;
			TextView title;
			TextView description;
		}
	}
}

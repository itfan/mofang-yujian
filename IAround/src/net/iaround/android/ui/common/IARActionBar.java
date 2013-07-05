package net.iaround.android.ui.common;

import net.iaround.android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class IARActionBar extends LinearLayout implements OnClickListener {

	private ImageView mIvArrow;
	private ImageView mIvIcon;
	private LinearLayout mLlParent;
	private TextView mTvTitle;
	private TextView mTvTitle2;
	private TextView mTvTitle3;
	private TextView mTvVisits;
	private TextView mTvMsgNum;
	private ImageView mIvFilterPointer;
	private ImageButton mIbMenu;

	private static final int TITLE = 0;
	private static final int USER = 1;
	private static final int MENU = 2;

	private int mStyle;
	private int mIcon;
	private String mTitle;
	private String mTitle2;
	private String mTitle3;
	private String mVisits;
	private String mMsgNum;
	private int mMenu;

	private PopupWindow mTitlePopupWindow;
	private View mTitleView;
	private ListView mTitleListView;
	private BaseAdapter mTitleAdapter;

	private OnBackClickListener mOnBackClickListener;
	private OnMenuClickListener mOnMenuClickListener;

	public static final int MODE_NONE = 0;
	public static final int MODE_LARGE = 1;
	private int mPopupWindowMode = MODE_NONE;

	public IARActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.IARActionBar);

		View titieView = LayoutInflater.from(context).inflate(
				R.layout.iar_action_bar, null);
		addView(titieView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		mIvArrow = (ImageView) titieView
				.findViewById(R.id.iar_acitonbar_title_arrows);
		mIvIcon = (ImageView) titieView
				.findViewById(R.id.iar_acitonbar_title_icon);
		mLlParent = (LinearLayout) titieView
				.findViewById(R.id.iar_acitonbar_title_parent);
		mTvTitle = (TextView) titieView
				.findViewById(R.id.iar_acitonbar_title_title);
		mTvTitle2 = (TextView) titieView
				.findViewById(R.id.iar_acitonbar_title_title2);
		mTvTitle3 = (TextView) titieView
				.findViewById(R.id.iar_acitonbar_title_title3);
		mTvVisits = (TextView) titieView
				.findViewById(R.id.iar_acitonbar_title_visits);
		mTvMsgNum = (TextView) titieView
				.findViewById(R.id.iar_acitonbar_title_msg_num);
		mIvFilterPointer = (ImageView) titieView
				.findViewById(R.id.iar_acitonbar_title_filterpointer);
		mIbMenu = (ImageButton) titieView
				.findViewById(R.id.iar_acitonbar_title_menu);

		mIcon = array.getResourceId(R.styleable.IARActionBar_icon, -1);
		mTitle = array.getString(R.styleable.IARActionBar_title);
		mTitle2 = array.getString(R.styleable.IARActionBar_title2);
		mTitle3 = array.getString(R.styleable.IARActionBar_title3);
		mVisits = array.getString(R.styleable.IARActionBar_visits);
		mMsgNum = array.getString(R.styleable.IARActionBar_msgnum);
		mMenu = array.getResourceId(R.styleable.IARActionBar_menu, -1);
		mStyle = array.getInt(R.styleable.IARActionBar_style, 0);

		initActionBar();

		mIvArrow.setOnClickListener(this);
		mLlParent.setOnClickListener(this);
		mIbMenu.setOnClickListener(this);
	}

	public IARActionBar(Context context) {
		super(context);
	}

	private void initActionBar() {
		switch (mStyle) {
		case TITLE:
			mIvFilterPointer.setVisibility(View.GONE);
			mTvVisits.setVisibility(View.GONE);
			mTvTitle2.setVisibility(View.GONE);
			if (mIcon != -1) {
				mIvIcon.setVisibility(View.VISIBLE);
				mIvIcon.setImageResource(mIcon);
			} else {
				mIvIcon.setVisibility(View.GONE);
			}
			if (mTitle != null) {
				mTvTitle.setText(mTitle);
			}
			if (mTitle3 != null) {
				mTvTitle3.setText(mTitle3);
			}
			if (mMsgNum != null) {
				mTvMsgNum.setVisibility(View.VISIBLE);
				mTvMsgNum.setText(mMsgNum);
			} else {
				mTvMsgNum.setVisibility(View.GONE);
			}
			if (mMenu != -1) {
				mIbMenu.setVisibility(View.VISIBLE);
				mIbMenu.setImageResource(mMenu);
			} else {
				mIbMenu.setVisibility(View.GONE);
			}

			break;

		case USER:
			mIvIcon.setVisibility(View.GONE);
			mIvFilterPointer.setVisibility(View.GONE);
			mTvMsgNum.setVisibility(View.GONE);
			mTvTitle3.setVisibility(View.GONE);
			if (mTitle != null) {
				mTvTitle.setText(mTitle);
			}
			if (mTitle2 != null) {
				mTvTitle2.setVisibility(View.VISIBLE);
				mTvTitle2.setText(mTitle2);
			} else {
				mTvTitle2.setVisibility(View.GONE);
			}
			if (mVisits != null) {
				mTvVisits.setVisibility(View.VISIBLE);
				mTvVisits.setText(mVisits);
			} else {
				mTvVisits.setVisibility(View.GONE);
			}
			if (mMenu != -1) {
				mIbMenu.setVisibility(View.VISIBLE);
				mIbMenu.setImageResource(mMenu);
			} else {
				mIbMenu.setVisibility(View.GONE);
			}
			break;

		case MENU:
			mTvVisits.setVisibility(View.GONE);
			mTvTitle3.setVisibility(View.GONE);
			if (mIcon != -1) {
				mIvIcon.setVisibility(View.VISIBLE);
				mIvIcon.setImageResource(mIcon);
			} else {
				mIvIcon.setVisibility(View.GONE);
			}
			if (mTitle != null) {
				mTvTitle.setText(mTitle);
			}
			if (mTitle2 != null) {
				mTvTitle2.setVisibility(View.VISIBLE);
				mTvTitle2.setText(mTitle2);
			} else {
				mTvTitle2.setVisibility(View.GONE);
			}
			if (mMsgNum != null) {
				mTvMsgNum.setVisibility(View.VISIBLE);
				mTvMsgNum.setText(mMsgNum);
			} else {
				mTvMsgNum.setVisibility(View.GONE);
			}
			if (mMenu != -1) {
				mIbMenu.setVisibility(View.VISIBLE);
				mIbMenu.setImageResource(mMenu);
			} else {
				mIbMenu.setVisibility(View.GONE);
			}

			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iar_acitonbar_title_arrows:
			if (mOnBackClickListener != null) {
				mOnBackClickListener.onBackClick();
			}
			break;
		case R.id.iar_acitonbar_title_parent:
			if (mStyle == MENU) {
				showTitlePopupwindow();
			} else {
				if (mOnBackClickListener != null) {
					mOnBackClickListener.onBackClick();
				}
			}
			break;
		case R.id.iar_acitonbar_title_menu:
			if (mOnMenuClickListener != null) {
				mOnMenuClickListener.onMenuClick();
			}
			break;
		default:
			break;
		}
	}

	public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
		mOnBackClickListener = onBackClickListener;
	}

	public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
		mOnMenuClickListener = onMenuClickListener;
	}

	public void setIcon(int icon) {
		mIcon = icon;
		mIvIcon.setImageResource(mIcon);
	}

	public void setTitle(CharSequence title) {
		mTitle = title.toString();
		mTvTitle.setText(title);
	}

	public void setTitle2(CharSequence title2) {
		mTitle2 = title2.toString();
		mTvTitle2.setText(title2);
	}

	public void setTitle3(CharSequence title3) {
		mTitle3 = title3.toString();
		mTvTitle3.setText(title3);
	}

	public void setMsgNum(CharSequence msgNum) {
		mMsgNum = msgNum.toString();
		mTvMsgNum.setText(msgNum);
	}

	public void setPopupWindowMode(int mode) {
		mPopupWindowMode = mode;
	}

	public void showMenu() {
		mIbMenu.setVisibility(View.VISIBLE);
	}

	public void dismissMenu() {
		mIbMenu.setVisibility(View.GONE);
	}

	public void showTitlePopupwindow() {
		mLlParent.setBackgroundResource(R.drawable.iar_title_shadow_bg);
		mIvFilterPointer.setImageResource(R.drawable.page_filter_pointer_up);

		mTitleView = LayoutInflater.from(getContext()).inflate(
				R.layout.title_popupwindow, null);
		int width = 0;
		switch (mPopupWindowMode) {
		case MODE_NONE:
			width = mLlParent.getWidth();
			break;

		case MODE_LARGE:
			width = mLlParent.getWidth() + 50;
			break;

		default:
			width = mLlParent.getWidth();
			break;
		}
		mTitlePopupWindow = new PopupWindow(mTitleView, width,
				LayoutParams.WRAP_CONTENT, true);
		mTitlePopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mTitleListView = (ListView) mTitleView
				.findViewById(R.id.title_pop_list);
		if (mTitleAdapter != null) {
			mTitleListView.setAdapter(mTitleAdapter);
		}
		mTitlePopupWindow.showAsDropDown(mLlParent, 0, 0);
		mTitlePopupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				mLlParent.setBackgroundResource(R.color.transparent);
				mIvFilterPointer
						.setImageResource(R.drawable.page_filter_pointer);
			}
		});
	}

	public void setTitleAdapter(BaseAdapter adapter) {
		mTitleAdapter = adapter;
	}

	public void dismissTitlePopupwindow() {
		mLlParent.setBackgroundResource(R.color.transparent);
		mIvFilterPointer.setImageResource(R.drawable.page_filter_pointer);
		if (mTitlePopupWindow != null && mTitlePopupWindow.isShowing()) {
			mTitlePopupWindow.dismiss();
		}
	}
}

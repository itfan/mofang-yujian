package net.iaround.android.ui.common;

import net.iaround.android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IARFocusImageView extends LinearLayout {

	private ImageView mImage;
	private LinearLayout mLayout;
	private ImageView mRemainTime;
	private TextView mTime;

	public IARFocusImageView(Context context) {
		super(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.iar_focus_imageview, null);
		addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mImage = (ImageView) view.findViewById(R.id.showimage);
		mLayout = (LinearLayout) view.findViewById(R.id.layout);
		mRemainTime = (ImageView) view.findViewById(R.id.remain_time);
		mTime = (TextView) view.findViewById(R.id.time);
	}

	public IARFocusImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.IARFocusImageView);
		View view = LayoutInflater.from(context).inflate(
				R.layout.iar_focus_imageview, null);
		addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mImage = (ImageView) view.findViewById(R.id.showimage);
		mLayout = (LinearLayout) view.findViewById(R.id.layout);
		mTime = (TextView) view.findViewById(R.id.time);
		init(array);
	}

	private void init(TypedArray array) {
		int margin = (int) array.getDimension(
				R.styleable.IARFocusImageView_marginBottom, -1);
		if (margin != -1) {
			RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) mLayout
					.getLayoutParams();
			layoutParams.setMargins(0, 0, 0, margin);
			mLayout.setLayoutParams(layoutParams);
		}

	}

	public void setImageBitmap(Bitmap bitmap) {
		mImage.setImageBitmap(bitmap);
	}

	public void setImageResource(int resId) {
		mImage.setImageResource(resId);
	}

	public void setText(CharSequence text) {
		if (TextUtils.isEmpty(text)) {
			mLayout.setVisibility(View.GONE);
		} else {
			mLayout.setVisibility(View.VISIBLE);
			mTime.setText(text);
		}
	}

	public void setMargin(int margin) {
		RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) mLayout
				.getLayoutParams();
		layoutParams.setMargins(0, 0, 0, margin);
		mLayout.setLayoutParams(layoutParams);
	}

	public void setBackgroundColor(int color) {
		mLayout.setBackgroundColor(color);
	}

	public void setBackgroundResource(int resid) {
		mLayout.setBackgroundResource(resid);
	}

	public void setVisibility(int visibility) {
		mRemainTime.setVisibility(visibility);
	}
}

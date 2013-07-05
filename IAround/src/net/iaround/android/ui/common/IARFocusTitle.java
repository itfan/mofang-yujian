package net.iaround.android.ui.common;

import net.iaround.android.R;
import net.iaround.android.util.ImageUtil;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class IARFocusTitle extends LinearLayout {

	private IARFocusImageView mImage;
	private IARFocusImageView mImageLeft;
	private IARFocusImageView mImageRight;
	private IARFocusImageView mImage1;
	private IARFocusImageView mImage2;
	private IARFocusImageView mImage3;
	private IARFocusImageView mImage4;

	private Bitmap mImageBitmap;
	private Bitmap mImageLeftBitmap;
	private Bitmap mImageRightBitmap;
	private Bitmap mImage1Bitmap;
	private Bitmap mImage2Bitmap;
	private Bitmap mImage3Bitmap;
	private Bitmap mImage4Bitmap;

	public IARFocusTitle(Context context) {
		super(context);
	}

	public IARFocusTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.IARFocusTitle);
		View titleView = LayoutInflater.from(context).inflate(
				R.layout.iar_focus_title, null);
		addView(titleView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		mImage = (IARFocusImageView) titleView.findViewById(R.id.image);
		mImageLeft = (IARFocusImageView) titleView
				.findViewById(R.id.image_left);
		mImageRight = (IARFocusImageView) titleView
				.findViewById(R.id.image_right);
		mImage1 = (IARFocusImageView) titleView.findViewById(R.id.image_1);
		mImage2 = (IARFocusImageView) titleView.findViewById(R.id.image_2);
		mImage3 = (IARFocusImageView) titleView.findViewById(R.id.image_3);
		mImage4 = (IARFocusImageView) titleView.findViewById(R.id.image_4);
		init(array);
	}

	private void init(TypedArray array) {
		int image = array.getResourceId(R.styleable.IARFocusTitle_image, -1);
		int image_left = array.getResourceId(
				R.styleable.IARFocusTitle_image_left, -1);
		int image_right = array.getResourceId(
				R.styleable.IARFocusTitle_image_right, -1);
		int image_1 = array
				.getResourceId(R.styleable.IARFocusTitle_image_1, -1);
		int image_2 = array
				.getResourceId(R.styleable.IARFocusTitle_image_2, -1);
		int image_3 = array
				.getResourceId(R.styleable.IARFocusTitle_image_3, -1);
		int image_4 = array
				.getResourceId(R.styleable.IARFocusTitle_image_4, -1);

		String imageText = array
				.getString(R.styleable.IARFocusTitle_image_text);
		String imageLeftText = array
				.getString(R.styleable.IARFocusTitle_image_left_text);
		String imageRightText = array
				.getString(R.styleable.IARFocusTitle_image_right_text);
		String image1Text = array
				.getString(R.styleable.IARFocusTitle_image_1_text);
		String image2Text = array
				.getString(R.styleable.IARFocusTitle_image_2_text);
		String image3Text = array
				.getString(R.styleable.IARFocusTitle_image_3_text);
		String image4Text = array
				.getString(R.styleable.IARFocusTitle_image_4_text);

		if (image != -1) {
			mImageBitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image), 15);
			mImage.setImageBitmap(mImageBitmap);
		}
		if (image_left != -1) {
			mImageLeftBitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_left),
					15);
			mImageLeft.setImageBitmap(mImageLeftBitmap);
		}
		if (image_right != -1) {
			mImageRightBitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_right),
					15);
			mImageRight.setImageBitmap(mImageRightBitmap);
		}
		if (image_1 != -1) {
			mImage1Bitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_1), 15);
			mImage1.setImageBitmap(mImage1Bitmap);
		}
		if (image_2 != -1) {
			mImage2Bitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_2), 15);
			mImage2.setImageBitmap(mImage2Bitmap);
		}
		if (image_3 != -1) {
			mImage3Bitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_3), 15);
			mImage3.setImageBitmap(mImage3Bitmap);
		}
		if (image_4 != -1) {
			mImage4Bitmap = ImageUtil.getFocusTitleImage(
					BitmapFactory.decodeResource(getResources(), image_4), 15);
			mImage4.setImageBitmap(mImage4Bitmap);
		}
		mImage.setText(imageText);
		mImageLeft.setText(imageLeftText);
		mImageRight.setText(imageRightText);
		mImage1.setText(image1Text);
		mImage2.setText(image2Text);
		mImage3.setText(image3Text);
		mImage4.setText(image4Text);
	}
}

package net.iaround.android.util;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Map;

import net.iaround.android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;

public class ImageUtil {
	public static final String NEARBY_PATH = "nearby/Images/";
	public static final String MEETGAME_PATH = "meetgame/Images/";
	public static final String FOCUS_PATH = "focus/Images/";
	public static final String VIP_PATH = "vip/Images/";
	public static final String MESSAGECENTER_PATH = "messagecenter/Images/";
	public static final String CONTACT_PATH="contact/Images/";

	public static Bitmap getCache(Context context,
			Map<String, SoftReference<Bitmap>> cache, String path, String name) {
		Bitmap bitmap = null;
		if (cache != null && name != null) {
			if (cache.containsKey(name)) {
				SoftReference<Bitmap> reference = cache.get(name);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			} else {
				bitmap = getLocation(context, cache, path, name);
			}
		}
		return bitmap;
	}

	public static Bitmap getLocation(Context context,
			Map<String, SoftReference<Bitmap>> cache, String path, String name) {
		Bitmap bitmap = null;
		if (path != null && name != null) {
			try {
				bitmap = BitmapFactory.decodeStream(context.getAssets().open(
						path + name));
			} catch (IOException e) {
				return null;
			}
		}
		if (cache != null && bitmap != null) {
			if (cache.containsKey(name)) {
				cache.remove(name);
				cache.put(name, new SoftReference<Bitmap>(bitmap));
			}
		}
		return bitmap;
	}

	public static Bitmap getNearBySex(Resources res, int sex) {
		switch (sex) {
		case 0:
			return BitmapFactory.decodeResource(res,
					R.drawable.space_sex_female);
		case 1:
			return BitmapFactory.decodeResource(res, R.drawable.space_sex_male);
		default:
			break;
		}
		return null;
	}

	public static Bitmap getNearBySmallSex(Resources res, int sex) {
		switch (sex) {
		case 0:
			return BitmapFactory.decodeResource(res, R.drawable.female);
		case 1:
			return BitmapFactory.decodeResource(res, R.drawable.male);
		default:
			break;
		}
		return null;
	}

	public static int getAgeBackground(int sex) {
		switch (sex) {
		case 0:
			return R.drawable.user_age_girl_bg;
		case 1:
			return R.drawable.user_age_boy_bg;
		default:
			break;
		}
		return 0;
	}

	public static Bitmap getMeetGameSex(Resources res, int sex) {
		switch (sex) {
		case 0:
			return BitmapFactory.decodeResource(res, R.drawable.sex_gril);
		case 1:
			return BitmapFactory.decodeResource(res, R.drawable.sex_boy);
		default:
			break;
		}
		return null;
	}

	public static Bitmap getFocusTitleImage(Bitmap bitmap, int pixels) {
		return createReflectedImage(toRoundCorner(bitmap, pixels));
	}

	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap createReflectedImage(Bitmap originalImage) {

		final int reflectionGap = 4;

		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + 20),
				Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);

		canvas.drawBitmap(originalImage, 0, 0, null);

		Paint defaultPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff,
				TileMode.MIRROR);

		paint.setShader(shader);

		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

}

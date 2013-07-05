package net.iaround.android.ui.meetgame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.ui.common.OnMenuClickListener;
import net.iaround.android.ui.common.Rotate3dAnimation;
import net.iaround.android.ui.common.Rotate3dAnimation.InterpolatedTimeListener;
import net.iaround.android.ui.vip.VipPrivilegeActivity;
import net.iaround.android.util.ImageUtil;
import net.iaround.android.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @fileName MeetGameFragmentBase.java
 * @package net.iaround.android.ui.meetgame
 * @description 邂逅游戏父类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public abstract class MeetGameFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener, OnMenuClickListener,
		InterpolatedTimeListener {
	/** 自定义标题栏 **/
	protected IARActionBar mIarActionBar;
	/** 个人档案按钮 **/
	protected LinearLayout mSeeFile;
	/** 送礼传情按钮 **/
	protected LinearLayout mSendGift;
	/** 想按钮 **/
	protected LinearLayout mWant;
	/** 不想按钮 **/
	protected LinearLayout mNoWant;

	/** 中间显示的内容 **/
	protected View mCentre;
	/** 照片 **/
	protected ImageView mCentreImage;
	/** 匹配度背景 **/
	protected RelativeLayout mCentreMatchingLayout;
	/** 匹配度文本 **/
	protected TextView mCentreMatching;
	/** 合拍度背景 **/
	protected LinearLayout mCentreAppearanceLayout;
	/** 合拍度文本 **/
	protected TextView mCentreAppearance;
	/** 机缘度背景 **/
	protected LinearLayout mCentreFateMatchingLayout;
	/** 机缘度文本 **/
	protected TextView mCentreFateMatching;
	/** 活跃度背景 **/
	protected LinearLayout mCentreConstellationLayout;
	/** 活跃度文本 **/
	protected TextView mCentreConstellation;
	/** 进度条 **/
	protected ProgressBar mCentreProgress;
	/** 标题 **/
	protected TextView mCentreTitle;
	/** 性别和年龄背景 **/
	protected RelativeLayout mCentreSexAgeLayout;
	/** 性别 **/
	protected ImageView mCentreSex;
	/** 年龄 **/
	protected TextView mCentreAge;
	/** 签名 **/
	protected TextView mCentreSignature;

	protected Handler mHandler;
	/** 匹配度动画 **/
	private AnimationDrawable mMatchingAnimationDrawable;

	/** 是否需要初始化 **/
	protected boolean mIsNeedInit = false;
	/** 匹配度动画图片 **/
	private static final int[] MATCHING_IMAGES = { R.drawable.meet_r0,
			R.drawable.meet_r1, R.drawable.meet_r2, R.drawable.meet_r3,
			R.drawable.meet_r4, R.drawable.meet_r5, R.drawable.meet_r6,
			R.drawable.meet_r7, R.drawable.meet_r8, R.drawable.meet_r9,
			R.drawable.meet_r10, R.drawable.meet_r11, R.drawable.meet_r12 };
	/** 匹配度单个图片的代表的数值 **/
	private static final int MATCHING = 100 / 13;
	/** 最大宽度 **/
	private int mMaxWidth;
	/** 匹配度显示的百分比数值 **/
	private int mShowMatchingNumber;
	/** 合拍度显示的百分比数值 **/
	private int mShowAppearanceNumber;
	/** 机缘度显示的百分比数值 **/
	private int mShowFateMatchingNumber;
	/** 活跃度显示的百分比数值 **/
	private int mShowConstellationNumber;

	public MeetGameFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
		/** 初始化Handler **/
		mHandler = new Handler();
		/** 获取最大的宽度90dip换算成px **/
		mMaxWidth = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 90, mActivity.getResources()
						.getDisplayMetrics());
	}

	protected View findViewById(int id) {
		if (mCentre != null) {
			if (mCentre.findViewById(id) != null) {
				return mCentre.findViewById(id);
			}
		}
		return super.findViewById(id);
	}

	/** 初始化邂逅游戏 **/
	protected void initMeetGame() {
		mIsNeedInit = true;
		// 获取随机邂逅游戏数据
		MeetGame meetGame = getRandomMeetGame();
		// 添加标题
		mCentreTitle.setText(meetGame.getName());
		// 添加年龄
		mCentreAge.setText(meetGame.getAge() + "");
		// 添加性别
		mCentreSex.setImageBitmap(ImageUtil.getMeetGameSex(getResources(),
				meetGame.getSex()));
		// 设置年龄性别的背景
		mCentreSexAgeLayout.setBackgroundResource(R.drawable.meet_sex_age_back);
		// 设置签名
		mCentreSignature.setText(meetGame.getSignature());

		startMatching(meetGame.getMatching());
		startProgress(meetGame.getAppearance(), meetGame.getFatematching(),
				meetGame.getConstellation());
		getImage(ImageUtil.getCache(mContext, mApplication.mMeetGameCache,
				ImageUtil.MEETGAME_PATH, meetGame.getImage()));
	}

	/** 获取邂逅游戏数据 **/
	protected void getMeetGame() {
		if (mApplication.mMeetGame == null) {
			mApplication.mMeetGame = new ArrayList<MeetGame>();
		}
		if (mApplication.mMeetGame.isEmpty()) {
			String json = TextUtil.getJosn(mContext, TextUtil.MEETGAME_PATH);
			try {
				JSONArray array = new JSONArray(json);
				MeetGame meetGame = null;
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					meetGame = new MeetGame();
					meetGame.setName(object.getString("name"));
					meetGame.setAge(object.getInt("age"));
					meetGame.setSex(object.getInt("sex"));
					meetGame.setSignature(object.getString("signature"));
					meetGame.setImage(object.getString("image"));
					meetGame.setMatching(object.getInt("matching"));
					meetGame.setAppearance(object.getInt("appearance"));
					meetGame.setFatematching(object.getInt("fatematching"));
					meetGame.setConstellation(object.getInt("constellation"));
					mApplication.mMeetGame.add(meetGame);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	/** 获取随机的邂逅游戏数据 **/
	protected MeetGame getRandomMeetGame() {
		if (mApplication.mMeetGame != null) {
			int random = (int) (Math.random() * mApplication.mMeetGame.size());
			random = random < mApplication.mMeetGame.size() ? random : 0;
			return mApplication.mMeetGame.get(random);
		}
		return new MeetGame();
	}

	/** 获取照片 **/
	protected void getImage(final Bitmap bitmap) {
		mCentreProgress.setVisibility(View.VISIBLE);
		mCentreImage.setImageResource(R.drawable.meet_default_image);
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				mCentreProgress.setVisibility(View.GONE);
				mCentreImage.setImageBitmap(bitmap);
			}
		}, 3000);
	}

	/** 开始匹配度动画 **/
	protected void startMatching(final int match) {

		mHandler.post(new Runnable() {

			@Override
			public void run() {
				mMatchingAnimationDrawable = new AnimationDrawable();
				int length = match / MATCHING;
				int l = length < MATCHING_IMAGES.length ? length
						: MATCHING_IMAGES.length;

				for (int i = 0; i < l; i++) {
					mMatchingAnimationDrawable.addFrame(getResources()
							.getDrawable(MATCHING_IMAGES[i]), 100);
				}
				mMatchingAnimationDrawable.setOneShot(true);
				mCentreMatchingLayout
						.setBackgroundDrawable(mMatchingAnimationDrawable);
				int time = (100 * l) / match;
				mShowMatchingNumber = 0;
				final Timer timer = new Timer();
				TimerTask task = new TimerTask() {

					@Override
					public void run() {
						if (mShowMatchingNumber < match) {
							mShowMatchingNumber++;
							mActivity.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									mCentreMatching.setText(mShowMatchingNumber
											+ "%");
								}
							});
						} else {
							timer.cancel();
						}

					}
				};
				timer.schedule(task, 0, time);
				mMatchingAnimationDrawable.start();
			}
		});
	}

	/** 开始合拍度、机缘度、活跃度动画 **/
	protected void startProgress(final int appearance, final int fatematching,
			final int constellation) {
		mCentreAppearanceLayout.getLayoutParams().width = (int) ((float) (mMaxWidth / 100f) * appearance);
		mCentreAppearanceLayout.invalidate();
		mCentreFateMatchingLayout.getLayoutParams().width = (int) ((float) (mMaxWidth / 100f) * fatematching);
		mCentreFateMatchingLayout.invalidate();
		mCentreConstellationLayout.getLayoutParams().width = (int) ((float) (mMaxWidth / 100f) * constellation);
		mCentreConstellationLayout.invalidate();
		ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f);
		anim.setFillAfter(true);
		anim.setDuration(2000);

		int appearanceTime = 2000 / appearance;
		int fateMatchingTime = 2000 / fatematching;
		int constellationTime = 2000 / constellation;
		mShowAppearanceNumber = 0;
		mShowFateMatchingNumber = 0;
		mShowConstellationNumber = 0;

		final Timer appearanceTimer = new Timer();
		TimerTask appearanceTask = new TimerTask() {

			@Override
			public void run() {
				if (mShowAppearanceNumber < appearance) {
					mShowAppearanceNumber++;
					mActivity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							mCentreAppearance.setText(mShowAppearanceNumber
									+ "%");
						}
					});
				} else {
					appearanceTimer.cancel();
				}

			}
		};
		final Timer fateMatchingTimer = new Timer();
		TimerTask fateMatchingTask = new TimerTask() {

			@Override
			public void run() {
				if (mShowFateMatchingNumber < fatematching) {
					mShowFateMatchingNumber++;
					mActivity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							mCentreFateMatching.setText(mShowFateMatchingNumber
									+ "%");
						}
					});
				} else {
					fateMatchingTimer.cancel();
				}

			}
		};
		final Timer constellationTimer = new Timer();
		TimerTask constellationTask = new TimerTask() {

			@Override
			public void run() {
				if (mShowConstellationNumber < constellation) {
					mShowConstellationNumber++;
					mActivity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							mCentreConstellation
									.setText(mShowConstellationNumber + "%");
						}
					});
				} else {
					constellationTimer.cancel();
				}

			}
		};

		mCentreAppearanceLayout.startAnimation(anim);
		mCentreFateMatchingLayout.startAnimation(anim);
		mCentreConstellationLayout.startAnimation(anim);
		appearanceTimer.schedule(appearanceTask, 0, appearanceTime);
		fateMatchingTimer.schedule(fateMatchingTask, 0, fateMatchingTime);
		constellationTimer.schedule(constellationTask, 0, constellationTime);
	}

	/** 3D旋转动画切换下一个 **/
	protected void startNext() {
		Rotate3dAnimation anim = new Rotate3dAnimation(
				mCentre.getWidth() / 2.0f, mCentre.getHeight() / 2.0f,
				Rotate3dAnimation.ROTATE_DECREASE);
		anim.setFillAfter(true);
		anim.setInterpolatedTimeListener(this);
		mCentre.startAnimation(anim);
	}

	/** 非VIP会员Dialog **/
	protected void showNoVipDialog() {
		showAlertDialog(getString(R.string.see_meetuser_info),
				getString(R.string.become_vip_can_see),
				android.R.drawable.ic_dialog_info, getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}, getString(R.string.to_be_vip),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						startActivity(VipPrivilegeActivity.class);
					}
				});
	}
}

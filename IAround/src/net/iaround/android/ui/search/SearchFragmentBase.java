package net.iaround.android.ui.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.TextView;
import net.iaround.android.BaseApplication;
import net.iaround.android.BaseFragment;
import net.iaround.android.R;
import net.iaround.android.ui.common.IARActionBar;
import net.iaround.android.ui.common.OnBackClickListener;
import net.iaround.android.ui.common.OnMenuClickListener;

public abstract class SearchFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener, OnMenuClickListener {

	protected IARActionBar mIarActionBar;
	protected TextView mAges;
	protected TextView mGenders;
	protected TextView mDistances;
	protected CheckedTextView mOnline;

	public SearchFragmentBase(BaseApplication application, Activity activity,
			Context context) {
		super(application, activity, context);
	}

	protected void showAgesDialog() {
		final String[] items = mContext.getResources().getStringArray(
				R.array.ages);
		new AlertDialog.Builder(mContext)
				.setTitle("选择年龄")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mAges.setText(items[which]);
								dialog.dismiss();
							}
						}).create().show();

	}

	protected void showGendersDialog() {
		final String[] items = mContext.getResources().getStringArray(
				R.array.genders);
		new AlertDialog.Builder(mContext)
				.setTitle("选择性别")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mGenders.setText(items[which]);
								dialog.dismiss();
							}
						}).create().show();

	}

	protected void showDistancesDialog() {
		final String[] items = mContext.getResources().getStringArray(
				R.array.distances);
		new AlertDialog.Builder(mContext)
				.setTitle("选择距离")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mDistances.setText(items[which]);
								dialog.dismiss();
							}
						}).create().show();

	}
}

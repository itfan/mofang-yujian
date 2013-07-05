package net.iaround.android.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class IARGridView extends GridView {

	public IARGridView(Context context) {
		super(context);
	}

	public IARGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public IARGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}

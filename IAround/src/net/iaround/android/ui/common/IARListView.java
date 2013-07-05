package net.iaround.android.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class IARListView extends ListView{

	public IARListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public IARListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IARListView(Context context) {
		super(context);
	}
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}

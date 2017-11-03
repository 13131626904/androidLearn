package com.gcfwpt.androidLearn.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gcfwpt.androidLearn.R;

public class BaseProgressBarLayout extends RelativeLayout {
	private View view;
	private ProgressBar imgProgress;
	private TextView tvProgress;
	private ScaleAnimation animation;
	public BaseProgressBarLayout(Context context) {
		super(context);
		addProgressView(context);
	}

	public BaseProgressBarLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		addProgressView(context);
	}
	private void addProgressView(Context context){
		view= View.inflate(context, R.layout.layout_base_progress, null);
		imgProgress=(ProgressBar)view.findViewById(R.id.imgProgress);
		tvProgress=(TextView)view.findViewById(R.id.tvProgress);
		addView(view);
	}
	public void show(){
		setVisibility(VISIBLE);
	}
	public void hide(){
		setVisibility(GONE);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return true;
	}
}

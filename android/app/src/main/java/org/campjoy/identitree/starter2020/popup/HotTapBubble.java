package org.campjoy.identitree.starter2020.popup;

import org.campjoy.identitree.starter2020.R;

import android.content.Context;

import android.graphics.Rect;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class HotTapBubble extends PopupWindows implements OnDismissListener {
	private ImageView mArrowUp;
	private ImageView mArrowDown;
	private Animation mTrackAnim;
	private LayoutInflater inflater;
	private ViewGroup mTrack;
	private OnDismissListener mDismissListener;
	
	private TextView mTitle;
	private TextView mDescription;
	
	private int mChildPos;    
    private int mAnimStyle;
    
	public static final int ANIM_GROW_FROM_LEFT = 1;
	public static final int ANIM_GROW_FROM_RIGHT = 2;
	public static final int ANIM_GROW_FROM_CENTER = 3;
	public static final int ANIM_AUTO = 4;
	
	/**
	 * Constructor.
	 * 
	 * @param context Context
	 */
	public HotTapBubble(Context context, String title, String description) {
		super(context);
		
		inflater 	= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mTrackAnim 	= AnimationUtils.loadAnimation(context, R.anim.rail);
		
		mTrackAnim.setInterpolator(new Interpolator() {
			public float getInterpolation(float t) {
	              // Pushes past the target area, then snaps back into place.
	                // Equation for graphing: 1.2-((x*1.6)-1.1)^2
				final float inner = (t * 1.55f) - 1.1f;
				
	            return 1.2f - inner * inner;
	        }
		});
	        
		setRootViewId(R.layout.hot_tap_bubble);
		
		mAnimStyle		= ANIM_AUTO;
		mChildPos		= 0;
		
		setTitle(title);
		setDescription(description);
	}
    
	/**
	 * Set root view.
	 * 
	 * @param id Layout resource id
	 */
	public void setRootViewId(int id) {
		mRootView	= (ViewGroup) inflater.inflate(id, null);
		
		mArrowDown         = (ImageView) mRootView.findViewById(R.id.arrow_down);
        mArrowUp         = (ImageView) mRootView.findViewById(R.id.arrow_up);

		//This was previously defined on show() method, moved here to prevent force close that occured
		//when tapping fastly on a view to show quickaction dialog.
		//Thanx to zammbi (github.com/zammbi)
		mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mTitle = (TextView) mRootView.findViewById(R.id.bubbletitle);
		mDescription = (TextView) mRootView.findViewById(R.id.bubbledescription);
		
		setContentView(mRootView);
	}

	/**
	 * Set animation style.
	 * 
	 * @param mAnimStyle animation style, default is set to ANIM_AUTO
	 */
	public void setAnimStyle(int mAnimStyle) {
		this.mAnimStyle = mAnimStyle;
	}
	
	public void setTitle(String title)
	{
		mTitle.setText(title);
	}
	
	public void setDescription(String description)
	{
		mDescription.setText(description);
	}
	
	/**
	 * Show popup mWindow
	 */
	public void show (View anchor, float xOffset, float yOffset) {
		preShow();

		int[] location 		= new int[2];
		
		anchor.getLocationOnScreen(location);

		Rect anchorRect 	= new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1] 
		                	+ anchor.getHeight());

		//mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		int rootWidth 		= mRootView.getMeasuredWidth();
		int rootHeight 		= mRootView.getMeasuredHeight();

		int screenWidth 	= mWindowManager.getDefaultDisplay().getWidth();
		//int screenHeight 	= mWindowManager.getDefaultDisplay().getHeight();

		int xPos 			= (screenWidth - rootWidth) / 2;
		int yPos	 		= anchorRect.top - rootHeight;

		boolean onTop		= true;
		
		// display on bottom
		if (rootHeight > anchor.getTop()) {
			yPos 	= anchorRect.bottom;
			onTop	= false;
		}

		showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up), (int)xOffset);
		
		setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);
	
		mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}

	/**
	 * Set animation style
	 * 
	 * @param screenWidth Screen width
	 * @param requestedX distance from left screen
	 * @param onTop flag to indicate where the popup should be displayed. Set TRUE if displayed on top of anchor and vice versa
	 */
	private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
		int arrowPos = requestedX - mArrowUp.getMeasuredWidth()/2;

		switch (mAnimStyle) {
		case ANIM_GROW_FROM_LEFT:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
			break;
					
		case ANIM_GROW_FROM_RIGHT:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
			break;
					
		case ANIM_GROW_FROM_CENTER:
			mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
		break;
					
		case ANIM_AUTO:
			if (arrowPos <= screenWidth/4) {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
			} else if (arrowPos > screenWidth/4 && arrowPos < 3 * (screenWidth/4)) {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
			} else {
				mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopDownMenu_Right : R.style.Animations_PopDownMenu_Right);
			}
					
			break;
		}
	}
	
	/**
	 * Show arrow
	 * 
	 * @param whichArrow arrow type resource id
	 * @param requestedX distance from left screen
	 */
	private void showArrow(int whichArrow, int requestedX) {
        final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp : mArrowDown;
        final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown : mArrowUp;

        final int arrowWidth = mArrowUp.getMeasuredWidth();

        showArrow.setVisibility(View.VISIBLE);
        
        ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)showArrow.getLayoutParams();
        
        param.leftMargin = requestedX - arrowWidth / 2;
      
        hideArrow.setVisibility(View.INVISIBLE);
    }
	
	/**
	 * Set listener for window dismissed. This listener will only be fired if the quicakction dialog is dismissed
	 * by clicking outside the dialog or clicking on sticky item.
	 */
	public void setOnDismissListener(HotTapBubble.OnDismissListener listener) {
		setOnDismissListener(this);
		
		mDismissListener = listener;
	}
	
	@Override
	public void onDismiss() {
		if (mDismissListener != null) {
			mDismissListener.onDismiss();
		}
	}
	/**
	 * Listener for window dismiss
	 * 
	 */
	public interface OnDismissListener {
		public abstract void onDismiss();
	}
}
package org.campjoy.identitree.starter.activities;

import java.io.IOException;

import org.campjoy.identitree.starter.CJApplication;
import org.campjoy.identitree.starter.MainActivity;
import org.campjoy.identitree.starter.MainTabActivity;
import org.campjoy.identitree.starter.R;
import org.campjoy.identitree.starter.model.GlossaryModel;
import org.campjoy.identitree.starter.model.TreeModel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity implements AnimationListener {
	private ImageView splashLayoutImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		CJApplication cjApplication = (CJApplication) getApplication();
		  requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
		setContentView(R.layout.activity_splash);
		
		splashLayoutImg = (ImageView)findViewById(R.id.splash_activity_image);
		TreeModel.loadInstance(getApplicationContext());
		GlossaryModel.loadInstance(getApplicationContext());

		try {
			splashLayoutImg.setBackgroundDrawable(Drawable.createFromStream(getAssets().open("american_basswood_img_0107.jpg"), null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);
		anim.setAnimationListener(this);
		
		splashLayoutImg.startAnimation(anim);
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		startActivity(new Intent(this, MainTabActivity.class));
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
		
	}
	
	
}

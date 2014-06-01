package com.example.scoreviewer2;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.os.Build;

import com.google.android.glass.eye.EyeGesture;
import com.google.android.glass.eye.EyeGestureManager;
import com.google.android.glass.eye.EyeGestureManager.Listener;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener {

    private static final String TAG = "EyeGestureDemo";		

    private GestureDetector gestureDetector;
    private EyeGestureManager mEyeGestureManager;
    private EyeGestureListener mEyeGestureListener;

    private EyeGesture target1 = EyeGesture.WINK;
    private EyeGesture target2 = EyeGesture.DOUBLE_BLINK;
    
    private int flag = 1;
    private static int pages = 13;
    
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
        mEyeGestureManager = EyeGestureManager.from(this);
        mEyeGestureListener = new EyeGestureListener();
        this.gestureDetector = new GestureDetector(this, this);
 

	}

    @Override
    protected void onStart() {
        super.onStart();

        mEyeGestureManager.stopDetector(target1);
        mEyeGestureManager.stopDetector(target2);

        mEyeGestureManager.enableDetectorPersistently(target1, true);
        mEyeGestureManager.enableDetectorPersistently(target2, true);

        mEyeGestureManager.register(target1, mEyeGestureListener);
        mEyeGestureManager.register(target2, mEyeGestureListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mEyeGestureManager.unregister(target1, mEyeGestureListener);
        mEyeGestureManager.unregister(target2, mEyeGestureListener);

        mEyeGestureManager.stopDetector(target1);
        mEyeGestureManager.stopDetector(target2);
    }

	
    private class EyeGestureListener  implements Listener  {

        @Override
        public void onEnableStateChange(EyeGesture eyeGesture, boolean paramBoolean) {
            Log.i(TAG, eyeGesture + " state changed:" + paramBoolean);
            //abortBroadcast();
        }

        @Override
        public void onDetected(final EyeGesture eyeGesture) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, eyeGesture + " is detected");
                    flag++;
                    if (flag > pages)
                    	flag = 1;
                    ImageView image = (ImageView) findViewById(R.id.imageView1);
                    String drawable = "event" + flag + "clar";
                    Log.i(TAG, "String is " + drawable);
                    int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                    Log.i(TAG, "resID is " + resID);
                    image.setImageResource(resID);
                }
                
            });
            
        }

    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "Tap detected.");
        flag--;
        if (flag < 1)
        	flag = pages;
        ImageView image = (ImageView) findViewById(R.id.imageView1);
        String drawable = "event" + flag + "clar";
        Log.i(TAG, "String is " + drawable);
        int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
        Log.i(TAG, "resID is " + resID);
        image.setImageResource(resID);
        return true;
	}
}


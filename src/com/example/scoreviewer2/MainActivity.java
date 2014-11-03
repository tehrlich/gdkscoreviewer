package com.example.scoreviewer2;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.glass.eye.EyeGesture;
import com.google.android.glass.eye.EyeGestureManager;
import com.google.android.glass.media.Sounds;

import java.util.Set;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private EyeGestureManager mEyeGestureManager;
    private EyeGestureListener mEyeGestureListener;

    private EyeGesture target1 = EyeGesture.WINK;
    private EyeGesture target2 = EyeGesture.DOUBLE_BLINK;
    
    private int flag = 1;
    private static int pages = 13;

    private final String TAG = "Main Activity";
    
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

        Log.d(TAG,"Player Part is:" + Settings.PLAYER_PART);

        // set the default view
        if (Settings.PLAYER_PART.equals("Clarinet")) {
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "event" + 1 + "clar";
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
        }
        else if (Settings.PLAYER_PART.equals("Conductor")){
            ImageView image = (ImageView) findViewById(R.id.imageView1);
            String drawable = "cond_1";
            int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
            image.setImageResource(resID);
        }
        else if (Settings.PLAYER_PART.equals("Drums")){
            ImageView image = (ImageView) findViewById(R.id.imageView1);
            String drawable = "drums_1";
            int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
            image.setImageResource(resID);
        }
        else if (Settings.PLAYER_PART.equals("Piano")){
            ImageView image = (ImageView) findViewById(R.id.imageView1);
            String drawable = "piano_1";
            int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
            image.setImageResource(resID);
        }
        else {
            Log.e(TAG,"Could not find player part");
        }
	}

    @Override
    protected void onStart() {
        super.onStart();

        mEyeGestureManager.register(target1, mEyeGestureListener);
        mEyeGestureManager.register(target2, mEyeGestureListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mEyeGestureManager.unregister(target1, mEyeGestureListener);
        mEyeGestureManager.unregister(target2, mEyeGestureListener);

    }

	
    private class EyeGestureListener implements EyeGestureManager.Listener {

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
                    iterateCards(true);
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
        //Log.i(TAG,"On Down detected");
        //Services.playSoundEffect(Sounds.DISMISSED);
        //this.finish();
		return false; // was false
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
        Log.i(TAG, "Single Tap Up detected.");
        iterateCards(false);
        return true;
	}

    private void iterateCards(boolean forward){
        if (Settings.PLAYER_PART.equals("Clarinet")) {
            Log.i(TAG, "iterate clarinet part");
            if (forward) {
                // iterate forward
                flag++;
                if (flag > Settings.TOTAL_PAGES)
                    flag = 1;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "event" + flag + "clar";
                Log.i(TAG, "String is " + drawable);
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                Log.i(TAG, "resID is " + resID);
                image.setImageResource(resID);
            } else {
                // iterate backwards
                flag--;
                if (flag < 1)
                    flag = Settings.TOTAL_PAGES;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "event" + flag + "clar";
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            }
        }
        else if (Settings.PLAYER_PART.equals("Conductor")){
            Log.i(TAG, "iterate Conductor part");
            if (forward) {
                // iterate forward
                flag++;
                if (flag > Settings.TOTAL_PAGES)
                    flag = 1;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "cond_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            } else {
                // iterate backwards
                flag--;
                if (flag < 1)
                    flag = Settings.TOTAL_PAGES;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "cond_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            }
        }
        else if (Settings.PLAYER_PART.equals("Drums")){
            Log.i(TAG, "iterate Drums part");
            if (forward) {
                // iterate forward
                flag++;
                if (flag > Settings.TOTAL_PAGES)
                    flag = 1;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "drums_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            } else {
                // iterate backwards
                flag--;
                if (flag < 1)
                    flag = Settings.TOTAL_PAGES;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "drums_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            }
        }
        else if (Settings.PLAYER_PART.equals("Piano")){
            Log.i(TAG, "iterate Piano part");
            if (forward) {
                // iterate forward
                flag++;
                if (flag > Settings.TOTAL_PAGES)
                    flag = 1;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "piano_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            } else {
                // iterate backwards
                flag--;
                if (flag < 1)
                    flag = Settings.TOTAL_PAGES;
                ImageView image = (ImageView) findViewById(R.id.imageView1);
                String drawable = "piano_" + flag;
                int resID = getResources().getIdentifier(drawable, "drawable", "com.example.scoreviewer2");
                image.setImageResource(resID);
            }
        } else {
            Log.e(TAG, "Could not find player part");
        }
    }

}



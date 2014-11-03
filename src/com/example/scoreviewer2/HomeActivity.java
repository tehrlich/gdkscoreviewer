package com.example.scoreviewer2;

/**
 * Created by calebadams on 11/3/14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.media.AudioManager;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.view.WindowUtils;
import java.util.List;

public class HomeActivity extends Activity {

    /** Gesture detector used to present the options menu. */
    private GestureDetector mGestureDetector;

    /** Used to handle screen changes smoothly. */
    private final Handler mHandler = new Handler();

    /** Tag for logging */
    private final String TAG = "Home Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Initialize the managers. */
        Services.setAudioManager((AudioManager) getSystemService(Context.AUDIO_SERVICE));
        mGestureDetector = new GestureDetector(this).setBaseListener(mBaseListener);

        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu){
        //if (featureId == FEATURE_VOICE_COMMANDS){
        getMenuInflater().inflate(R.menu.main, menu);
        //return true;
        //}
        // Pass through to super to setup touch menu.
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureID, MenuItem item) {
        /** Posting through a handler gives the
         *  menu a chance to slide off screen
         *  before moving on
         */
        switch (item.getItemId()) {

            case R.id.conductor:
                mHandler.post(new Runnable() {
                    @Override
                    public void run(){
                        Settings.PLAYER_PART = getResources().getString(R.string.conductor);
                        Settings.TOTAL_PAGES = 13;
                        startWithPart();
                        Services.playSoundEffect(Sounds.SUCCESS);
                    }
                });
                return true;

            case R.id.keys:
                mHandler.post(new Runnable() {
                    @Override
                    public void run(){
                        Settings.PLAYER_PART = getResources().getString(R.string.keys);
                        Settings.TOTAL_PAGES = 13;
                        startWithPart();
                        Services.playSoundEffect(Sounds.SUCCESS);
                    }
                });
                return true;

            case R.id.drums:
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Settings.PLAYER_PART = getResources().getString(R.string.drums);
                        Settings.TOTAL_PAGES = 13;
                        startWithPart();
                        Services.playSoundEffect(Sounds.SUCCESS);
                    }
                });
                return true;

            case R.id.clarinet:
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Settings.PLAYER_PART = getResources().getString(R.string.clarinet);
                        Settings.TOTAL_PAGES = 13;
                        startWithPart();
                        Services.playSoundEffect(Sounds.SUCCESS);
                    }
                });
                return true;

            case R.id.action_settings:
                mHandler.post(new Runnable() {
                    @Override
                    public void run(){
                        showSettings();
                        Services.playSoundEffect(Sounds.SUCCESS);
                    }
                });
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    /** Starts the "Settings" activity */
    public void showSettings(){
        //Intent intent = new Intent(this, SettingsActivity.class);
        //startActivity(intent);
        finish();
    }


    /** Starts the "Scan Item" activity */
    public void startWithPart() {
        startActivity(new Intent(this, MainActivity.class));
        //finish();
    }

    /** Handles gestures while not in the options menu */
    private final com.google.android.glass.touchpad.GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            if (gesture == Gesture.TAP) {
                Log.i(TAG,"Tep Detected");
                Services.playSoundEffect(Sounds.TAP);
                openOptionsMenu();
                return true;
            } else {
                return false;
            }
        }
    };
}
package com.example.scoreviewer2;

import android.media.AudioManager;

/**
 * Created by calebadams on 11/3/14.
 */
public class Services {
    private static AudioManager mAudioManager;

    public static void setAudioManager(AudioManager a){
        mAudioManager = a;
    }

    public static void playSoundEffect(int sound){
        mAudioManager.playSoundEffect(sound);
    }
}

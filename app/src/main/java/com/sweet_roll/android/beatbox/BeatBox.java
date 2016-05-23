package com.sweet_roll.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trand_000 on 5/15/2016.
 */
public class BeatBox {
    public static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox (Context context)
    {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);//MAX_SOUNDS: maximum sounds can be played at once. STREAM_MUSIC: kind of audio will be played
                                                                             //last parameter '0' specifies quality of sample rate converter and is ignored
        loadSounds();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId ==null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);//sound id, volume left, volume right, priority (ignored in this case),loop or not, playback rate
    }


    private void loadSounds()
    {
        String[] soundNames;
        try
        {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch(IOException ioe)
        {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
        for(String fileName : soundNames)
        {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe){
                Log.e(TAG,"Could not load sound" + fileName, ioe);
            }
        }
    }
    //load up SoundPool with actual sounds
    private void load(Sound sound) throws IOException{//throw exception if sounds not found
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetsPath());//get the path to the our sounds
        int soundId = mSoundPool.load(afd, 1);//calling SoundPool.load(..) loads a file into SoundPool
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds()
    {
        return mSounds;
    }
}

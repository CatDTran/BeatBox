package com.sweet_roll.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by trand_000 on 5/15/2016.
 */
public class BeatBox {
    public static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;

    public BeatBox (Context context)
    {
            mAssets = context.getAssets();
        loadSound();
    }
    private void loadSound()
    {
        String[] soundNames;
        try
        {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch(IOException ioe)
        {
            Log.e(TAG, "Could not list assests", ioe);
            return;
        }
    }
}
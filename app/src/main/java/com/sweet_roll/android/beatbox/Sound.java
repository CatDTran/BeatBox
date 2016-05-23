package com.sweet_roll.android.beatbox;

/**
 * Created by trand_000 on 5/15/2016.
 */
public class Sound {
    private String mAssetsPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assetsPath)
    {
        mAssetsPath = assetsPath;
        String[] components = assetsPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav", "");
    }
    public String getAssetsPath()
    {
        return mAssetsPath;
    }

    public String getName()
    {
        return mName;
    }

    public Integer getSoundId(){
        return mSoundId;
    }

    public void setSoundId(Integer soundId){
        mSoundId = soundId;
    }

}

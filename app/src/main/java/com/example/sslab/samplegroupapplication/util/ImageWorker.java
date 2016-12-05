package com.example.sslab.samplegroupapplication.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Ala on 2016-11-27.
 */

public abstract class ImageWorker {
    private static final String TAG = "ImageWorker";
    private static final int FADE_IN_TIME = 200;

    private ImageCache mImageCache;
    private ImageCache.ImageCacheParams mImageCacheParams;

    protected Resources mResources;

    protected ImageWorker(Context context) {
        mResources = context.getResources();
    }



}

package com.example.sslab.samplegroupapplication.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * A BitmapDrawable that keeps track of whether it is being displayed or cached.
 * When the drawable is no longer being displayed or cached,
 * {@link android.graphics.Bitmap#recycle() recycle()} will be called on this drawable's bitmap.
 *
 * BitmapDrawable은 보여질 것인지 캐시되어있을 건지에 대해 지속적으로 관리한다.
 * drawable이 더이상 보여지지않거나 캐싱되어있지 않는경우,
 * {@link android.graphics.Bitmap#recycle() recycle()} 이 비트맵상에서 불려질 것이다.
 */

public class RecyclingBitmapDrawable extends BitmapDrawable{
    static final String TAG = "CountingBitmapDrawable";

    private int mCacheRefCount = 0;
    private int mDisplayRefCount = 0;

    private boolean mHasBeenDisplayed;

    public RecyclingBitmapDrawable(Resources res, Bitmap bitmap) {
        super(res, bitmap);
    }


    private synchronized void checkState() {
        //BEGIN_INCLUDE(check_state)
        // If the drawable cache and display ref counts = 0, and this drawable
        // has been displayed, then recycle
        if (mCacheRefCount <= 0 && mDisplayRefCount <= 0 && mHasBeenDisplayed
                && hasValidBitmap()) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "No longer being used or cached so recycling. "
                        + toString());
            }

            getBitmap().recycle();
        }
        //END_INCLUDE(check_state)
    }

    /**
     * 가져온 비트맵이 null이 아니고 비트맵이
     * @return 유효한 비트맵인지 아닌지에 대한 boolean값을 리턴한다.
     */
    private synchronized boolean hasValidBitmap() {
        Bitmap bitmap = getBitmap();
        return bitmap != null && !bitmap.isRecycled();
    }

}

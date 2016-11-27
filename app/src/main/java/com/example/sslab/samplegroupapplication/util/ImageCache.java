package com.example.sslab.samplegroupapplication.util;

import android.graphics.Bitmap;

/**
 * Created by Ala on 2016-11-27.
 */

public class ImageCache {
    private static final String TAG = "ImageCache";

    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 5; // 5MB

    // Default disk cache size in bytes
    private static final int DEFAULT_DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

    // Compression settings when writing images to disk cache
    private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    private static final int DEFAULT_COMPRESS_QUALITY = 70;
    private static final int DISK_CACHE_INDEX = 0;

}

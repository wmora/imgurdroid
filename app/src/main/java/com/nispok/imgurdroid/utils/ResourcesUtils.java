package com.nispok.imgurdroid.utils;

import android.support.annotation.StringRes;

import static com.nispok.imgurdroid.Imgurdroid.getApplication;

public class ResourcesUtils {

    /**
     * Return a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     */
    public static String getString(@StringRes int resId) {
        return getApplication().getString(resId);
    }

}

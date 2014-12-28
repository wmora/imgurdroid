package com.nispok.imgurdroid;

import android.app.Application;

public class Imgurdroid extends Application {

    private static Imgurdroid application;

    public Imgurdroid() {
        super();
        application = this;
    }

    public static Imgurdroid getApplication() {
        return application;
    }

}

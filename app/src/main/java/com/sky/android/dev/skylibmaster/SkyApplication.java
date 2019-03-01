package com.sky.android.dev.skylibmaster;

import android.app.Application;
import org.sky.adid.dev.SkyCrashHandle;
import org.sky.adid.dev.SkySPUtils;

/**
 * Copyright (c) 2018 Sky. All rights reserved.
 */
public class SkyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //This is Sky Crash called code for logger info on TF-card
        SkyCrashHandle.getInstance().init(this);
        //This is Sky SharedPreference called code for sp
        SkySPUtils.getInstance().init(this);
    }
}

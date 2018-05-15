package com.scleroidtech.gatepass;

import android.app.Application;

import com.scleroidtech.gatepass.di.AppComponent;
import com.scleroidtech.gatepass.di.AppInjector;
import com.scleroidtech.gatepass.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

/**
 * Copyright (C) 3/9/18
 * Author ganesh
 */

/**
 * We create a custom{@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        AppInjector.init(this);
        //    JobManagerFactory.getJobManager(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);

        return appComponent;
    }

}

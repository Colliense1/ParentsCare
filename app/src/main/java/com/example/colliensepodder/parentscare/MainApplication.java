package com.example.colliensepodder.parentscare;

import android.app.Application;
import android.content.Context;

import com.example.colliensepodder.parentscare.Helper.LocalHelper;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base,"en"));
    }
}

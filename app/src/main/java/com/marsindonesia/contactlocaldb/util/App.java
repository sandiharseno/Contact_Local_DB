package com.marsindonesia.contactlocaldb.util;

import android.app.Application;
import android.content.Context;

import com.marsindonesia.contactlocaldb.db.DBHelper;

/**
 * Created by Sandi on 10/7/2017.
 */
public class App extends Application {
    private static Context context;
    private static DBHelper mEventDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mEventDbHelper = new DBHelper(getContext());
        mEventDbHelper.open();
    }

    public static Context getContext() {
        return context;
    }

    public static DBHelper getDbHelper() {
        return mEventDbHelper;
    }
}

package com.wordpress.priyankvex.couchbasesimplesample;

import android.app.Application;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

/**
 * Created by priyank on 1/2/16.
 */
public class ApplicationController extends Application{

    Manager mManager;
    Database mDatabase;
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // create a manager
        try {
            mManager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            return;
        }
        // create database
        try {
            mDatabase = mManager.getDatabase(Config.DATABASE_NAME);
            Log.d (Config.TAG, "Database created");

        } catch (CouchbaseLiteException e) {
            Log.e(Config.TAG, "Cannot get database");
        }
    }

    public Database getDatabase(){
        return this.mDatabase;
    }

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

}

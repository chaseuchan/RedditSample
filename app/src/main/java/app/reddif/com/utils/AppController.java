package app.reddif.com.utils;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by chan4u on 8/29/2018.
 */


public class AppController extends MultiDexApplication {
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(AppController.this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}

package com.iindicar.indicar.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.iindicar.indicar.view.main.login.KakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

//import io.fabric.unity.android.FabricInitializer;
import io.fabric.unity.android.fabricinit.BuildConfig;

public class App extends Application {

    private static volatile App instance = null;
    private static volatile Activity currentActivity = null;

    private int windowWidth;
    private int windowHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());

        //FabricInitializer.initializeFabric(this, FabricInitializer.Caller.Android);
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        App.currentActivity = currentActivity;
    }

    public static App getGlobalApplicationContext() {
        if(instance == null) {
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
        //minSdkVersion <= 20
//        MultiDex.install(this);
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }
}

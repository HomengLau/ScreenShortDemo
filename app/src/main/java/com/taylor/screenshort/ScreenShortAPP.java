package com.taylor.screenshort;

import android.app.Application;
import android.content.Intent;
import android.os.Looper;
import android.os.MessageQueue;

import com.taylor.commonjar.contentProvider.Global;

/**
 * Created by liuhq13 on 2017/6/22.
 */

public class ScreenShortAPP extends Application {
    private static ScreenShortAPP instance;

    public static ScreenShortAPP getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Global.init(this);
        //LeakCanary.install(this);
//        CrashHandler.getInstance().init(this);
        /*Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {

                KeepAliveWatcher.keepAlive(BigBangApp.this);
                startService(new Intent(BigBangApp.this, ListenClipboardService.class));
                startService(new Intent(BigBangApp.this, BigBangMonitorService.class));
                return false;
            }
        });*/
        //AppManager.getInstance(this);
    }
}

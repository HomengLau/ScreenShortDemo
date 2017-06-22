package com.taylor.screenshort;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.taylor.commonjar.contentProvider.ConstantUtil;
import com.taylor.commonjar.contentProvider.SPHelper;

import static com.taylor.commonjar.contentProvider.ConstantUtil.BROADCAST_FLOAT_BAR_SERVICE_MODIFIED;

/**
 * Created by liuhq13 on 2017/6/22.
 */

public class FloatBarServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_FLOAT_BAR_SERVICE_MODIFIED);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void adjustService() {
        boolean isForground = SPHelper.getBoolean(ConstantUtil.SHOW_FLOAT_VIEW, false);
        if (isForground) {
            if (!isForegroundShow) {
                handler.removeCallbacksAndMessages(null);
                if (bigbangNotification == null) {
                    bigbangNotification = new BigbangNotification(this);
                }
                bigbangNotification.setContetView();
                startForeground(NOTIFYID, bigbangNotification.getNotification());
                isForegroundShow = true;
                isGrayGuardOn=false;
            }
        } else {
            stopForeground();
        }
}

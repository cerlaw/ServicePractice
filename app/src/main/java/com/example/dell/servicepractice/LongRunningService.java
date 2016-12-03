package com.example.dell.servicepractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by DELL on 2016/12/2.
 */

public class LongRunningService extends Service {

    private final String TAG = "LongRunningService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG","executed at " + new Date().toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(Context.
                ALARM_SERVICE);
        int aMinute = 60 * 1000;
        long triggerTime = SystemClock.elapsedRealtime() + aMinute;
        Intent i = new Intent(this,AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pi);
        Log.d(TAG,"executed at " + new Date().toString());
        return super.onStartCommand(intent, flags, startId);
    }
}

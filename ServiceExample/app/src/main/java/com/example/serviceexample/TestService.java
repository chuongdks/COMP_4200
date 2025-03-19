package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test_service", "Service created: onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("test_service", "Service created: onStartCommand()");
        stopSelf(); // automatically stop once background task is done
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test_service", "Service created: onDestroy()");
    }
}

package com.example.serviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestIntentService extends IntentService {
    public TestIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("test_service", "Intent Handled: onHandleIntent()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test_service", "Intent Handled: onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("test_service", "Intent Handled: onStartCommand()");
        stopSelf(); // automatically stop once background task is done
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test_service", "Intent Handled: onDestroy()");
    }
}

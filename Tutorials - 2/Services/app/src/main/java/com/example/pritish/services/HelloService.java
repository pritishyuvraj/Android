package com.example.pritish.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class HelloService extends Service {
    int mStartMode;
    IBinder mBinder;
    boolean mAllowRebind;

//    Called when the service is being created
    @Override
    public void onCreate() {

    }

//    Service is starting, due to calling startService()

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Let it continue running until it is stopped
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

//    Client binding to the service with bindService()
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    Called when all clients have unbounded with unbindService()

    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

//    Called when a client is binding to the service with bindService()

    @Override
    public void onRebind(Intent intent) {

    }

//    Called when the service is no longer used and is being destroyed

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Sevice Destroyed", Toast.LENGTH_LONG).show();
    }
}

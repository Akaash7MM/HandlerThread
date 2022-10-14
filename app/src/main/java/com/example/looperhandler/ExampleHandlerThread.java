package com.example.looperhandler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

public class ExampleHandlerThread extends HandlerThread {

    public static final int EXAMPLE_TASK = 1;

    //handler Thread doesnt have Handler by default but a looper
    private Handler handler;

    public ExampleHandlerThread() {
        super("ExampleHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
    }

    //Handler is called here right after Looper.prepare is called by the HandlerThread
    // Otherwise the loop will start and no code will get executed
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case EXAMPLE_TASK:
                        Log.d("ExampleThread", "arg1: " + msg.arg1 + "obj: " + msg.obj);
                        for (int i = 0; i < 4; i++) {
                            Log.d("ExampleThread", "handlemessage: " + i);
                            SystemClock.sleep(1000);
                        }
                        break;
                }
            }
        };
    }

    public Handler getHandler() {
        return handler;
    }
}
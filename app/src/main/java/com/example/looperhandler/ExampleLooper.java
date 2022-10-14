package com.example.looperhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

public class ExampleLooper extends Thread{
    private final static String TAG = "ExampleLooperThread";

    //Handler associated with thread it is instaniated in
    public Handler handler;

    @Override
    public void run(){

//        for(int i = 0 ; i<5;i++){
//
//            Log.d("RUN","RUN:"+i);
//            SystemClock.sleep(1000);
//        }

        //Adds Looper and message Quere
        //Need this before handler or handler wont find a looper for this thread
        Looper.prepare();

        handler = new Handler();

        Looper.loop();

        Log.d(TAG,"End of run");
    }
}

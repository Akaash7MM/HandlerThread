package com.example.looperhandler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread{

    private static final String TAG = "SimpleWorker";
    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedDeque<Runnable> taskQueue = new ConcurrentLinkedDeque<>();

     public SimpleWorker(){
         super(TAG);
         start();
     }

    @Override
    public void run() {
        while(alive.get()){
            Runnable task = taskQueue.poll();
            if(task!=null){
                task.run();
            }
        }
        Log.d(TAG,"Simple Worker Terminated");
    }

    public SimpleWorker execute(Runnable task){
         taskQueue.add(task);
         return this;
    }
    public void quit(){
         alive.set(false);
    }
}

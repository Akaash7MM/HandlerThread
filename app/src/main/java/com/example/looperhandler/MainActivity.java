package com.example.looperhandler;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.looperhandler.databinding.ActivityMainBinding;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;

    private ExampleLooper looper = new ExampleLooper();
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread();
//    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerThread.start();
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleAsyncTask asyncTask = new ExampleAsyncTask(MainActivity.this);
                asyncTask.execute(10);

            }
        });


        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = Message.obtain(handlerThread.getHandler());
                msg.what = 1;
                msg.arg1 = 123;
                msg.obj = "Obj String";

                handlerThread.getHandler().sendMessage(msg);

            }
        });
            // Can send either a Runnable or a message object that need to be run in the run method
//          handlerThread.getHandler().postDelayed(new ExampleRunnable2(),6600);
//          handlerThread.getHandler().post(new ExampleRunnable1());
//          handlerThread.getHandler().postAtFrontOfQueue(new ExampleRunnable2());




        //postdelayed
//        threadHandler.postAtFrontOfQueue()
//        handlerThread.getHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0 ; i<5;i++){
//                    Log.d("MainActivity","Run:"+i);
//                }
//
//            }
//        });







        //if looper already running it will crash if button is pressed again i.e looper called again
        // Cannot start same thread again
//        binding.button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                looper.start();
//            }
//        });
//
//
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                looper.handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        for(int i  = 0; i<5;i++){
//                            Log.d("RUN","run :" + i);
//
//                        }
//                    }
//                });
//            }
//        });

//        worker.execute(() ->{
//            try{
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Message message = Message.obtain();
//            message.obj = "task 1 Complete";
//            handler.handleMessage(message);
//
//        }).execute(() ->{
//            try{
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Message message = Message.obtain();
//            message.obj = "task 2 Complete";
//            handler.handleMessage(message);
//
//        }).execute(() -> {
//            try{
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Message message = Message.obtain();
//            message.obj = "task 3 Complete";
//            handler.handleMessage(message);
//
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class ExampleRunnable1 implements Runnable{
        @Override
        public void run(){
            for (int i =0 ;i<4;i++){
                Log.d("ExpRun","Run1:"+i);
                SystemClock.sleep(1000);
            }
        }
    }
    static class ExampleRunnable2 implements Runnable{
        @Override
        public void run(){
            for (int i =0 ;i<4;i++){
                Log.d("ExpRun","Run2:"+i);
                SystemClock.sleep(1000);
            }
        }
    }

}
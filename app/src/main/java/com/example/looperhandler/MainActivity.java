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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    ActivityMainBinding binding;

    private ExampleLooper looper = new ExampleLooper();
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(this);
        binding.sendButton.setOnClickListener(this);
        binding.asyncStart.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    @Override
    public void onClick(View view) {

        if(view==binding.startButton){
            handlerThread.start();
        }
        else if(view==binding.sendButton){
            Message msg = Message.obtain(handlerThread.getHandler());
            msg.what = 1;
            msg.arg1 = 123;
            msg.obj = "Obj String";
            handlerThread.getHandler().sendMessage(msg);
            handlerThread.getHandler().postDelayed(new ExampleRunnable2(),6600);
            handlerThread.getHandler().post(new ExampleRunnable1());
            handlerThread.getHandler().postAtFrontOfQueue(new ExampleRunnable2());
        }
        else if(view == binding.asyncStart){
            ExampleAsyncTask asyncTask = new ExampleAsyncTask(MainActivity.this);
            asyncTask.execute(10);
        }
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
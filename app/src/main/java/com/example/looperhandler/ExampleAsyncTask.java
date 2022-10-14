package com.example.looperhandler;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {
    final WeakReference<MainActivity> activityWeakReference;

    ExampleAsyncTask(MainActivity activity) {
        activityWeakReference = new WeakReference<MainActivity>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Strong refrence released asa the method is compelete
        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        activity.binding.progressBar.setVisibility(View.VISIBLE);
    }

    //First parameter, things passed to the AsyncTask
    //
    @Override
    protected String doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++) {
            //Loop progress
            publishProgress((i * 100) / integers[0]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Fin";
    }

    //Can access UI thread
    //Takes values from the publishprogress method ran in doInbackground
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        activity.binding.progressBar.setProgress(values[0]);
    }

    //Gets return type of doinbackground as return type
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        activity.binding.progressBar.setProgress(0);
        activity.binding.progressBar.setVisibility(View.INVISIBLE);
    }
}

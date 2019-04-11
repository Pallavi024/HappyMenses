package com.example.root.happymenses;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Vibrator;
import android.widget.Toast;

public class reminder extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(reminder.this, "Happy Menses!!!!", Toast.LENGTH_LONG).show();

        Vibrator vibrator;
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(15000);


        return false;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

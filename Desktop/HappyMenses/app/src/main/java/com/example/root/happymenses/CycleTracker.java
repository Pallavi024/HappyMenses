package com.example.root.happymenses;

import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

public class CycleTracker extends AppCompatActivity {

    public static final int JOB_ID = 123;
    int daystoadd = 0;
    int day,month,year,d,m,y,h,mi, sec;
    Button btn;
    Calendar c;

    {
        c = Calendar.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_tracker);
        final boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"),
                PendingIntent.FLAG_NO_CREATE) == null);
        btn=findViewById(R.id.button2);
    }

    public void currentscenario(View v) {
        c.add(Calendar.DATE,daystoadd);
        day=c.get(Calendar.DATE);
        month=c.get(Calendar.MONTH);
        year=c.get(Calendar.YEAR);

        Toast.makeText(CycleTracker.this, "your date is marked", LENGTH_LONG).show();
        remind r = new remind();
        r.execute();
    }

    public class remind extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                Calendar ca = Calendar.getInstance();
                d = ca.get(Calendar.DATE);
                m = ca.get(Calendar.MONTH);
                // m += 1;
                y = ca.get(Calendar.YEAR);
                h = ca.get(Calendar.HOUR);
                mi = ca.get(Calendar.MINUTE);
                sec=ca.get(Calendar.SECOND);
                if (d == day && m == month && y == year && h==11 && mi==9 && sec==0) {


                    schedulejob();
                    break;
                }
            }
            return null;
        }



        private void schedulejob() {

            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(CycleTracker.this, reminder.class))
                    .setOverrideDeadline(0)
                    .setPersisted(true)
                    .build();

            jobScheduler.schedule(jobInfo);


        }
    }
}







package com.example.root.happymenses;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.root.happymenses.data.Signup;

public class SplashActivity extends AppCompatActivity {

    private int SleepTimer = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
        VideoView videoview = (VideoView) findViewById(R.id.mVideoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.xyz2mp4);
        videoview.setVideoURI(uri);
        videoview.start();
    }
    private class LogoLauncher extends Thread{
        public void run()
        {
            try{
                sleep(1000*SleepTimer);
            }
            catch(InterruptedException e){

                e.printStackTrace();
        }
            Intent i=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
            SplashActivity.this.finish();
        }
    }
}

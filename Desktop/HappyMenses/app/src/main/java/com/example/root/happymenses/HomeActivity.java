package com.example.root.happymenses;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView =(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);

            // Start the new activity
            startActivity(homeIntent);
            Toast.makeText(this,"This is Home",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.cycle)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent cycleIntent = new Intent(HomeActivity.this, CycleTracker.class);

            // Start the new activity
            startActivity(cycleIntent);
            Toast.makeText(this,"This is Cycle Tracker",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.chat)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent chatIntent = new Intent(HomeActivity.this, Chat.class);

            // Start the new activity
            startActivity(chatIntent);
            Toast.makeText(this,"Chat with an experienced Doctor",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.products)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent productIntent = new Intent(HomeActivity.this,Products.class);

            // Start the new activity
            startActivity(productIntent);
            Toast.makeText(this,"Everything you want at one place!!",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.modules)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent modulesIntent = new Intent(HomeActivity.this, Modules.class);

            // Start the new activity
            startActivity(modulesIntent);
            Toast.makeText(this,"Answers To all your Questions!!",Toast.LENGTH_LONG).show();
        }
        if(id == R.id.log)
        {
            // Create a new intent to open the {@link NumbersActivity}
            Intent logsIntent = new Intent(HomeActivity.this, HomeActivity.class);

            // Start the new activity
            startActivity(logsIntent);
            Toast.makeText(this,"Logged out",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}


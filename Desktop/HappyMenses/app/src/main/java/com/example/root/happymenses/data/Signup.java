package com.example.root.happymenses.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.happymenses.HomeActivity;
import com.example.root.happymenses.MainActivity;
import com.example.root.happymenses.R;

public class Signup extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button signup;
    EditText email,pass,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        openHelper=new DatabaseHelper(this);
        signup=findViewById(R.id.signup);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(name.getText().length()<2){
                    name.setError("Error");
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.getText().length()<2){
                    email.setError("Error");
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(pass.getText().length()<2){
                    pass.setError("Error");
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length()<2 || pass.getText().length()<2 ||  email.getText().length()<2){
                    Toast.makeText(getApplicationContext(),"fill all the fields!!",Toast.LENGTH_LONG).show();
                }
                else{
                    db=openHelper.getWritableDatabase();
                    String Name=name.getText().toString();
                    String Email=email.getText().toString();
                    String Pass=pass.getText().toString();
                    insertdata(Name,Email,Pass);
                    Intent signupIntent = new Intent(Signup.this, HomeActivity.class);

                    // Start the new activity

                    Toast.makeText(getApplicationContext(),"Signed Up successfully",Toast.LENGTH_LONG).show();
                    signupIntent.putExtra("Names",Name);
                    startActivity(signupIntent);
                }
            }
        });
    }

    public void insertdata(String Name,String Email,String Pass){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,Email);
        contentValues.put(DatabaseHelper.COL_3,Pass);
        contentValues.put(DatabaseHelper.COL_4,Name);
        long id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }
}

package com.example.root.happymenses.data;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.happymenses.HomeActivity;
import com.example.root.happymenses.R;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button login;
    EditText email,pass;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper=new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Pass=pass.getText().toString();
                cursor=db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME +" WHERE " + DatabaseHelper.COL_2 + "=? AND "
                        + DatabaseHelper.COL_3 + "=?",new String[]{Email,Pass});
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Login.this,HomeActivity.class);
                        String name=cursor.getString(3);
                        i.putExtra("Names",name);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"CREDENTIALS DOES NOT MATCH!!!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

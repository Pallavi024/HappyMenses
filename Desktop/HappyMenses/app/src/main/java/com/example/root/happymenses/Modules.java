package com.example.root.happymenses;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Modules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.info);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent infoIntent = new Intent(Modules.this, InfoActivity.class);

                // Start the new activity
                startActivity(infoIntent);
                Toast.makeText(view.getContext(),"Know what happens to your body during menstruation",Toast.LENGTH_SHORT).show();

            }
        });

        // Find the View that shows the family category
        TextView family = (TextView) findViewById(R.id.dos);

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent dosIntent = new Intent(Modules.this, DosActivity.class);

                // Start the new activity
                startActivity(dosIntent);
                Toast.makeText(view.getContext(),"Do's and Dont's",Toast.LENGTH_SHORT).show();

            }
        });

        // Find the View that shows the colors category
        TextView colors = (TextView) findViewById(R.id.disorders);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ColorsActivity}
                Intent dosIntent = new Intent(Modules.this, DisordersActivity.class);

                // Start the new activity
                startActivity(dosIntent);
                Toast.makeText(view.getContext(),"Menstrual disorders",Toast.LENGTH_SHORT).show();

            }
        });

        // Find the View that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.myths);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent mythsIntent = new Intent(Modules.this, MythsActivity.class);

                // Start the new activity
                startActivity(mythsIntent);
                Toast.makeText(view.getContext(),"Common Myths",Toast.LENGTH_SHORT).show();

            }
        });
    }
}


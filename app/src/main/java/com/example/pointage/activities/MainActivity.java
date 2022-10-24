package com.example.pointage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pointage.R;
import com.example.pointage.utilies.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sessionManager = new SessionManager(getApplicationContext());

        final EditText editTextPassword = findViewById(R.id.editTextPassword);
        final EditText editPassword = findViewById(R.id.editTextPassword);
        Button buttonIns = findViewById(R.id.buttonIns);
        Button buttonValider = findViewById(R.id.buttonValider);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Inscription.class));

            }
        });

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextPassword.getText().toString().trim().equals("")){

                    Toast.makeText(getApplicationContext(),R.string.thak,Toast.LENGTH_SHORT).show();
                }
                else if (editPassword.getText().toString().trim().equals(sessionManager.getPassword())){

                    startActivity(new Intent(getApplicationContext(),Accueil.class));
                    finish();
                }

                else {

                    Toast.makeText(getApplicationContext(),"Mot de passe incorrect",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    }


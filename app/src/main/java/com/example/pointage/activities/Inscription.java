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

public class Inscription extends AppCompatActivity {

    SessionManager sessionManager;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Button buttonV = findViewById(R.id.buttonV);
        final EditText editCle = findViewById(R.id.editCle);
        final EditText editPassword = findViewById(R.id.editPassword);

        sessionManager = new SessionManager(getApplicationContext());

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editCle.getText().toString().trim().equals("") || editPassword.getText().toString().trim().equals("")){

                    Toast.makeText(getApplicationContext(),R.string.thak,Toast.LENGTH_SHORT).show();

                }
                else
                if (editCle.getText().toString().trim().equals("cisix")){

                    sessionManager.setPassword(editPassword.getText().toString().trim());
                    startActivity(new Intent(getApplicationContext(),Accueil.class));
                    finish();

                }
                else {

                    Toast.makeText(getApplicationContext(),"la clé n'est pas correct",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

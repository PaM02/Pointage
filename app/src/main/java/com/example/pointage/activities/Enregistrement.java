package com.example.pointage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pointage.R;
import com.example.pointage.adapter.HighTechItemAdapter;
import com.example.pointage.modeles.HighTechItem;
import com.example.pointage.utilies.Variables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Enregistrement extends AppCompatActivity {

    HighTechItemAdapter highTechItemAdapter;
    ListView listEn;
    public static List<HighTechItem> highTechitemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);

        listEn = findViewById(R.id.listEn);

        new QuerytoMysql().execute("");
    }

    @SuppressLint("StaticFieldLeak")
    public  class QuerytoMysql extends AsyncTask<String, String, String> {

        String msg;
        Boolean aBoolean=false;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {

            progress = ProgressDialog.show(Enregistrement.this, "Connexion en cours","Merci de patienter..", true);

        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(Exception e) {
            }
            try{

                Connection connection = DriverManager.getConnection(Variables.url,Variables.user,Variables.passe);
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM personne where etat='p'";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {

                    try {
                        highTechitemList.add(new HighTechItem(resultSet.getString("prenom"),resultSet.getString("nom")));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    msg = "Connexion r??ussi";

                }

                msg = "Connexion r??ussi";
                aBoolean = true;
                connection.close();

            }

            catch (Exception e){

                //si la connexion ne passe pas
                msg = "Une connexion r??seau est n??cessaire pour effectuer cette t??che.Veuillez vous connecter ?? un r??seau Wi-Fi ou cellulaire.";


            }


            return msg;

        }

        @Override
        protected void onPostExecute(String s) {


            progress.dismiss();

            if (aBoolean){

                try {

                    highTechItemAdapter = new HighTechItemAdapter(Enregistrement.this,highTechitemList);
                    listEn.setAdapter(highTechItemAdapter);

                } catch (Exception ex) {

                }
            }

            else{

                Toast.makeText(Enregistrement.this,s,Toast.LENGTH_SHORT).show();

            }

        }
    }
}

package com.example.pointage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Lista extends AppCompatActivity {

    HighTechItemAdapter highTechItemAdapter;
    ListView shopListViewA;
    public static List<HighTechItem> highTechitemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        shopListViewA = findViewById(R.id.shopListViewA);
        QuerytoMysql querytoMysql = new QuerytoMysql();
        querytoMysql.execute("");

        Button buttonValiderA = findViewById(R.id.buttonValiderA);
        buttonValiderA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuerytoMysql querytoMysql = new QuerytoMysql();
                querytoMysql.execute("");
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    public  class QuerytoMysql extends AsyncTask<String, String, String> {

        String msg;
        Boolean aBoolean=false;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {

            progress = ProgressDialog.show(Lista.this, "Connexion en cours","Merci de patienter..", true);

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
                String query = "SELECT * FROM personne where etat='a'";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {

                    try {
                        highTechitemList.add(new HighTechItem(resultSet.getString("prenom"),resultSet.getString("nom")));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    msg = "Connexion réussi";

                }

                msg = "Connexion réussi";
                aBoolean = true;
                connection.close();

            }
            catch (Exception e){

                //si la connexion ne passe pas
                msg = "Une connexion réseau est nécessaire pour effectuer cette tâche.Veuillez vous connecter à un réseau Wi-Fi ou cellulaire.";


            }


            return msg;

        }

        @Override
        protected void onPostExecute(String s) {


            progress.dismiss();

            if (aBoolean){

                try {

                    highTechItemAdapter = new HighTechItemAdapter(Lista.this,highTechitemList);
                    shopListViewA.setAdapter(highTechItemAdapter);

                } catch (Exception ex) {

                }
            }

            else{

                Toast.makeText(Lista.this,s,Toast.LENGTH_SHORT).show();

            }

        }
    }
}
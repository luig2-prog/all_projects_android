package com.example.labcortedosactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUser;
    private EditText editTextPassword;
    private String user;
    private String password;
    private Button btnIngresar;
    private ProgressBar progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enlazarViews();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task1().execute(editTextUser.getText().toString());
                user = editTextUser.getText().toString();
                password = editTextPassword.getText().toString();
            }
        });

    }

    private void enlazarViews(){
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnIngresar = (Button) findViewById(R.id.button);
        progreso = (ProgressBar) findViewById(R.id.progressBar);

    }

    private void login(){

        if ((user.equals("admin"))&&(password.equals("admin"))){

            Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String datetime = dateformat.format(c.getTime());

            startActivity(intent);

            Toast.makeText(this,"Ingreso con exito",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"datos erroneos o faltantes",Toast.LENGTH_LONG).show();
        }

    }

    private class Task1 extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            progreso.setVisibility(View.VISIBLE);
            btnIngresar.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {

            progreso.setVisibility(View.INVISIBLE);
            btnIngresar.setEnabled(true);
            login();

        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }
    }

}
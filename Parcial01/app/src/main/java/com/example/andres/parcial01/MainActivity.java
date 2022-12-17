package com.example.andres.parcial01;

import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText User, Pass;
    private Button Log;
    private ProgressBar progreso;
    private String nombre, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User = (EditText) findViewById(R.id.editTextUser);
        Pass = (EditText) findViewById(R.id.editTextPass);
        Log = (Button) findViewById(R.id.buttonLogin);
        progreso = (ProgressBar) findViewById(R.id.progressBar);

        Log.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                new Task1().execute(User.getText().toString());
                nombre = User.getText().toString();
                clave = Pass.getText().toString();



            }


        });

    }

    private void login(){

        if ((nombre.equals("root"))&&(clave.equals("1234"))){

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            //intent.putExtra("usua", User.getText().toString());

            //Toast.makeText(this,"correcto",Toast.LENGTH_LONG).show();
            startActivity(intent);


        }else {
            Toast.makeText(this,"datos erroneos o faltantes",Toast.LENGTH_LONG).show();
        }

    }

    class Task1 extends AsyncTask<String,Void,String> {


        @Override
        protected void onPreExecute() {
            progreso.setVisibility(View.VISIBLE);
            Log.setEnabled(false);

        }

        @Override
        protected void onPostExecute(String s) {

            progreso.setVisibility(View.INVISIBLE);
            Log.setEnabled(true);
            login();



        }

        @Override
        protected String doInBackground(String... strings) {


            try{

                Thread.sleep(2000);

            }catch (InterruptedException e){

                e.printStackTrace();


            }

            return strings[0];
        }
    }
}

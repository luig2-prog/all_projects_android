package com.example.imagenesasyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsuario, txtPassword;
    private Button Ingresar;
    private ProgressBar progreso;
    private String name, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText)findViewById(R.id.edit_Usu);
        txtPassword =(EditText)findViewById(R.id.edit_Pas);
        progreso = (ProgressBar) findViewById(R.id.progressBar);
        Ingresar =(Button)findViewById(R.id.button);
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task1().execute(txtUsuario.getText().toString());{
                    name = txtUsuario.getText().toString();
                    contraseña= txtPassword.getText().toString();
                }
            }
        });
    }
    public class Task1 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progreso.setVisibility(View.VISIBLE);
            Ingresar.setEnabled(false);

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            progreso.setVisibility(View.INVISIBLE);
            Ingresar.setEnabled(true);
            Login();


        }
    }
    public void Login(){
        if ((name.equals("admin"))&&(contraseña.equals("admin"))){

            Intent intent = new Intent(MainActivity.this, CargarImagenes.class);
            //intent.putExtra("usua", User.getText().toString());

            //Toast.makeText(this,"correcto",Toast.LENGTH_LONG).show();
            startActivity(intent);


        }else {
            Toast.makeText(this,"Usuario o Contaseña Invalidos",Toast.LENGTH_LONG).show();
        }

    }

}


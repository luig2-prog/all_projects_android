package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPassword;
    private Button btnIniciarSesion;
    private TextView recoverPassword;
    private String name;
    private String password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        recoverPassword = (TextView) findViewById(R.id.txtRecoverPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                name = txtUser.getText().toString();
                password = txtPassword.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);



            }

        });

   }


   //public void ingreso(View view){

    //}


}
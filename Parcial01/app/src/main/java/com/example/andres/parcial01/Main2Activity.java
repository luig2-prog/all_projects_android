package com.example.andres.parcial01;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main2Activity extends AppCompatActivity {

    private EditText n1;
    private EditText n2;
    private RadioButton potencia;
    private RadioGroup operaciones;
    private  RadioButton division;
    private RadioButton multiplicacion;
    private String numero1, numero2;
    private Button calcular;
    private Button salir;
    private  int resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        n1 = (EditText) findViewById(R.id.editTextNum1);
        n2 = (EditText) findViewById(R.id.editTextNum2);
        division = (RadioButton) findViewById(R.id.radioButtondiv);
        multiplicacion = (RadioButton) findViewById(R.id.radioButtonMult);
        potencia = (RadioButton) findViewById(R.id.radioButtonPot);
        calcular = (Button) findViewById(R.id.buttonCalcular);
        salir = (Button) findViewById(R.id.buttonBack);


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new operar().execute();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private double operaciones(){
        String numero1 = n1.getText().toString();
        String numero2 = n2.getText().toString();

        double primero = Double.parseDouble(numero1);
        double segundo = Double.parseDouble(numero2);
        double total = 0;
        if (multiplicacion.isChecked()){
            total = primero*segundo;
        }else if (division.isChecked()){
            double totald;
            totald = primero/segundo;

            total = Math.round(totald * 100d)/100d;
           
        }else if (potencia.isChecked()){

            total = Math.pow(primero,segundo);


        }

        return total;
    }



    class operar extends AsyncTask<Double, Void, Double> {


        @Override
        protected Double doInBackground(Double... strings) {
            return operaciones();

        }


        @Override
        public void onPostExecute(Double s) {


            Toast.makeText(Main2Activity.this, "Resultado de la operacion:" + s, Toast.LENGTH_SHORT).show();

        }
    }

}

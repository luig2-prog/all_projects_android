package com.example.clase01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText_1;
    private EditText editText_2;
    private String cadena_1, cadena_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_1 = (EditText) findViewById(R.id.txtCadena_1);
        editText_2 = (EditText) findViewById(R.id.txtCadena_2);
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.btnComparar:
                cadena_1 = editText_1.getText().toString();
                cadena_2 = editText_2.getText().toString();

                if(cadena_1.equalsIgnoreCase(cadena_2)){
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Las cadenas no coinciden",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



}
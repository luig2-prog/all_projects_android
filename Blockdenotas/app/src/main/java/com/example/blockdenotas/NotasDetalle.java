package com.example.blockdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NotasDetalle extends AppCompatActivity {

    private TextView titulo;
    private EditText descripcion;
    private Notas notas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_detalle);

        titulo = (TextView) findViewById(R.id.textTituloDetalle);
        descripcion = (EditText) findViewById(R.id.textDesciptionDetalle);
        notas = (Notas) getIntent().getExtras().getSerializable("itemDetalle");

        titulo.setText(notas.getTitulo());
        descripcion.setText(notas.getDescripcion());
    }





}
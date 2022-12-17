package com.example.cajondenavegacion;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Detalles extends AppCompatActivity {

    private EditText titulo;
    private EditText detalles;

    private Notas nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        mostrarNota();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                toolbar.setTitle("hduewihdew");
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detalles.this, MainActivity.class);
                startActivity(intent);

            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setImageResource(R.drawable.ic_menu_gallery);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void mostrarNota(){
        titulo = (EditText) findViewById(R.id.textTituloDetalles);
        detalles = (EditText) findViewById(R.id.textDetalleDetalles);
        nota = (Notas) getIntent().getExtras().getSerializable("itemDetalle");
        titulo.setText(nota.getTitulo());
        detalles.setText(nota.getDescripcion());
    }
}
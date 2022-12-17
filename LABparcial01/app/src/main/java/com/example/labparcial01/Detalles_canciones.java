package com.example.labparcial01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Detalles_canciones extends AppCompatActivity {

    private TextView nombreDetalles, artistaDetalles, añoDetalles, detalles;
    private ImageView imagenCancionDetalles;
    private Canciones cancionesDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_canciones);

        enlazarViews();

        new Task3().execute();

    }

    private void enlazarViews(){
        nombreDetalles = (TextView) findViewById(R.id.textNombreDetalles);
        artistaDetalles = (TextView) findViewById(R.id.textArtistaDetalles);
        añoDetalles = (TextView) findViewById(R.id.textAñoDetalles);
        imagenCancionDetalles = (ImageView) findViewById(R.id.imagenCancionDetalles);
        detalles = (TextView) findViewById(R.id.textDetalles);
        cancionesDetalles = (Canciones) getIntent().getExtras().getSerializable("itemDetail");
    }

    private void mostrarDetalles(){
        imagenCancionDetalles.setImageResource(cancionesDetalles.getImagenCantante());
        nombreDetalles.setText(cancionesDetalles.getNombre());
        artistaDetalles.setText(cancionesDetalles.getArtista());
        añoDetalles.setText(cancionesDetalles.getAño()+"");
        detalles.setText(cancionesDetalles.getCaracteristicas());
    }

    private class Task3 extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected String doInBackground(String... strings) {
            mostrarDetalles();
            return null;
        }
    }

}
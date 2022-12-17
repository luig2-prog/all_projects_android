package com.example.parcial1laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleProducto extends AppCompatActivity {

    private TextView nombre, descrp;
    private ImageView foto;
    private Cls_Producto productolistado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        nombre = (TextView) findViewById(R.id.txtDetallesNombre);
        descrp = (TextView) findViewById(R.id.txtDetallesDescripcion);
        foto = (ImageView) findViewById(R.id.Imgfoto);

        productolistado = (Cls_Producto) getIntent().getExtras().getSerializable("detallesp");

        new Task3().execute("detalleproducto");
    }

    void Verproductos(){
        nombre.setText(productolistado.getNombre());
        descrp.setText(productolistado.getDescripcion());
        foto.setImageResource(productolistado.getFoto());
    }

    class Task3 extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {


        }

        @Override
        protected String doInBackground(String... strings) {
            Verproductos();
            return strings[0];
        }
    }
}
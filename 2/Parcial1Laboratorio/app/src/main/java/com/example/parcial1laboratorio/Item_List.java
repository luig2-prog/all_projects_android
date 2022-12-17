package com.example.parcial1laboratorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Item_List extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private Cls_Adpatador customAdapterProductos;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__list);
        cardView = (CardView) findViewById(R.id.cardprincipal);

        new Task2().execute("datos");
    }

    public void CargarProductos(){

        recyclerViewProductos = (RecyclerView) findViewById(R.id.RecyclerIdproductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));
        customAdapterProductos = new Cls_Adpatador(Datos_Productos());
        recyclerViewProductos.setAdapter(customAdapterProductos);
    }

    class Task2 extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected String doInBackground(String... strings) {
            CargarProductos();
            return strings[0];
        }
    }

    public List<Cls_Producto> Datos_Productos() {
        List<Cls_Producto> productos = new ArrayList<>();
        productos.add(new Cls_Producto("Teclados","Teclados gamer de todas las marcas y modelos.",R.drawable.teclado));
        productos.add(new Cls_Producto("Monitores","Monitores de todas las marcas y modelos",R.drawable.monitor));
        productos.add(new Cls_Producto("Logitech","Sistema de audio envolvente 5.1 cuenta con un cine en casa",R.drawable.sonido));
        productos.add(new Cls_Producto("Procesador","Procesadores Intel de Decima generacion",R.drawable.procesador));
        productos.add(new Cls_Producto("Memorias RAM","Memoria ram RGB de todas las frecuencias",R.drawable.ram));
        return productos;
    }
}
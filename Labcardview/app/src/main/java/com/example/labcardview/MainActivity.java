package com.example.labcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewproductos;
    private  RecyclerViewAdaptador  adaptadorProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewproductos = (RecyclerView)findViewById(R.id.listaRecview);
        recyclerViewproductos.setLayoutManager(new LinearLayoutManager(this));

        adaptadorProducto = new RecyclerViewAdaptador(ObtenerProducto());

        recyclerViewproductos.setAdapter(adaptadorProducto);

    }


    public List<Productos> ObtenerProducto(){

        List<Productos> producto = new ArrayList<>();
        producto.add(new Productos("Aseo","para limpieza", R.mipmap.ic_launcher));
        producto.add(new Productos("Comestibles","para consumo", R.mipmap.ic_launcher));
        producto.add(new Productos("Aseo2","para limpieza", R.mipmap.ic_launcher));
        producto.add(new Productos("Comestibles2","para consumo", R.mipmap.ic_launcher));
        producto.add(new Productos("Aseo3","para limpieza", R.mipmap.ic_launcher));
        producto.add(new Productos("Comestibles3","para consumo", R.mipmap.ic_launcher));
        producto.add(new Productos("Aseo4","para limpieza", R.mipmap.ic_launcher));
        producto.add(new Productos("Comestibles4","para consumo", R.mipmap.ic_launcher));
        producto.add(new Productos("Aseo5","para limpieza", R.mipmap.ic_launcher));
        producto.add(new Productos("Comestibles5","para consumo", R.mipmap.ic_launcher));





        return producto;
    }




}

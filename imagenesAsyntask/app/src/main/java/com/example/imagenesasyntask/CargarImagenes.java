package com.example.imagenesasyntask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CargarImagenes extends AppCompatActivity {
    RecyclerView ReciclerLista;
    ArrayList<CelularesModelo> Celulares;
    Adaptador adaptadorRecyclerview;
    String Urlimagen[];
    public static Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargar_imagenes);
        getSupportActionBar().setTitle("Usuario: Admin");
        ReciclerLista = findViewById(R.id.recicler_View);
        Celulares = new ArrayList<>();
        ReciclerLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Urlimagen =new String[] {"http://i.linio.com/p/d6986534960baaed7d96c6e52fb2eec2-product.jpg\n" +
                "http://i.linio.com/p/f4e4a73ef523715ffe7026f66132d58b-product.jpg\n" +
                "http://i.linio.com/p/be8767c88e3a05863c8a564b9aafec0f-product.jpg\n" +
                "http://i.linio.com/p/578c5586ab4329c28c10e487937a51ff-product.jpg\n" +
                "http://i.linio.com/p/f1dda17e13c3eee2da76acf6d0ff9521-product.jpg\n" +
                "http://i.linio.com/p/afc2f3f8d6a592bc17cd651e47e17cdb-product.jpg" };
        new AsynctaskCarga(this).execute(Urlimagen);



    }
    public class AsynctaskCarga extends AsyncTask<String, Void, String> {

        private Context contexto;
        private ProgressDialog proceso;

        public AsynctaskCarga(Context context) {

            contexto = context;
            Celulares.add(new CelularesModelo("Xiaomi Red Note9", "Pantalla: 6.65 pulgadas, Memoria interna: 128 GB, Camara frontal de16 Mpx, Camara trasera de 64 Mpx"));
            Celulares.add(new CelularesModelo("Iphone 12", "Pantalla: 6.7, Memoria interna: 512 GB, camara frontal y trasera de 12Mpx"));
            Celulares.add(new CelularesModelo("Motorola Edge 5", "Pantalla: 6.7 pulgadas, Memoria interna: 256 GB, Camara frontal de 25 Mpx, Camara trasera de 64 Mpx"));
            Celulares.add(new CelularesModelo("Samsung Galaxy71", "Pantalla: 6.7 pulgadas, Memoria interna: 128 GB, Camara Cuadruple"));
            Celulares.add(new CelularesModelo("Xiaomi Poco X3", "Pantalla: 6.65 pulgadas, Memoria interna: 128 GB, Camara frontal de20 Mpx, Camara trasera de 64 Mpx"));
            Celulares.add(new CelularesModelo("Huawei P40", "Pantalla: 6.4 pulgadas, Memoria interna: 128 GB, Camara frontal  16 Mpx, Camara trasera de 48Mpx"));
             //Celulares.add(new CelularesModelo("Iphone 11 Pro Max", "Pantalla: 6.5 pulgadas, Memoria interna: 256 GB, Camara frontal de 12 Mpx, Camara trasera de 12 Mpx"));
            //Celulares.add(new CelularesModelo("Resiste Cat", "Pantalla: 6.65 pulgadas, Memoria interna: 128 GB, Camara frontal de20mpx, Camara trasera de 64Mpx"));



        }

        @Override
      protected String doInBackground(String... strings) {

        for (int i = 0; i < strings.length; i++) {
            Celulares.get(i).imgCelular= downloadBitmap(strings[i]);
        }
        return null;
       }

      @Override
      protected void onPostExecute(String inte) {
        proceso.dismiss();
        adaptadorRecyclerview= new Adaptador(Celulares);
        ReciclerLista.setAdapter(adaptadorRecyclerview);

    }

      @Override
      protected void onPreExecute() {
        proceso = new ProgressDialog(contexto);
        proceso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        proceso.setMessage("Cargando Celulares Top ...");
        proceso.setCancelable(false);
        proceso.show();

    }

        private Bitmap downloadBitmap(String url) {
          HttpURLConnection urlConnection = null;
          try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
             }
               } catch (Exception e) {
               urlConnection.disconnect();

            }
          finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                                      }
                  }
             return null;
         }
    }
}





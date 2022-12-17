package com.example.luisgarcia.lab05;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    String todo="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    texto = (EditText)findViewById(R.id.listadotxt);
    String archivos[] = fileList();


    if( encontrado(archivos,"listados.txt" )   ){

       leer();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String datetime = dateformat.format(c.getTime());

        guardar(datetime + " Ingreso registrado");

    }



    }

    private void leer(){
        try{
            InputStreamReader archivo =  new InputStreamReader(openFileInput("listados.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();


            while(linea != null){

                todo = todo + linea +"\n";
                linea = br.readLine();
            }

            br.close();
            archivo.close();
            texto.setText(todo);

        }catch (IOException e){


        }
    }

    @SuppressLint("NewApi")
    private boolean encontrado(String archivos [], String  fichero){


       for ( int i =0; i < archivos.length ; i++ ){

           if(fichero.equals(archivos[i])){
                File file = new File(fichero);
               System.out.println(file.toPath().getFileName());
               Toast.makeText(this,"" + file.getAbsolutePath(),Toast.LENGTH_LONG).show();
                return true;

           }


       }

        return  false;
    }



    public void guardar(String contenido){

        String contenido2 = todo + contenido;

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("listados.txt", Activity.MODE_PRIVATE));
            archivo.write(contenido2);
            todo = "";

            archivo.close();
            leer();

        }catch (IOException e){

        }

        //Toast.makeText(this,"                Ingreso guardado\n" + contenido,Toast.LENGTH_LONG).show();


    }



}

package com.example.archivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        String nombreArchivo = "LogRegistro";

        if(existe(nombreArchivo)){

            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String datetime = dateformat.format(c.getTime());

            escribir(nombreArchivo,datetime);
           editText.setText(leer(nombreArchivo));
        }

    }



    public boolean existe(String nombreArchivo){
        File archivo = new File(nombreArchivo);

        if(!archivo.exists()){
            try {
                boolean b = archivo.canWrite();
                System.out.println("- -----------------\n" + b);
                archivo.createNewFile();
                archivo.setWritable(true)

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void escribir(String nombreArchi, String contenido) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nombreArchi, Context.MODE_PRIVATE));

            archivo.write(contenido);

            archivo.close();

        }catch (Exception e){
            System.out.println(e);
        }







        ///---------------------------------------------




        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            File file = new File(nombreArchi);

            fileWriter = new FileWriter(file.getAbsoluteFile(),true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(contenido);

            Toast.makeText(this,"Información agregada",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (fileWriter != null){
                    fileWriter.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }


    }

    public String leer(String nombreArchi){
        String todo="";
        try{
            InputStreamReader archivo =  new InputStreamReader(openFileInput(nombreArchi));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();


            while(linea != null){
                todo = todo + linea +"\n";
                linea = br.readLine();
            }

            br.close();
            archivo.close();


        }
        catch (IOException e){


        }
        return todo;
    }
}
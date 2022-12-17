package com.example.labcortedosactividad.ui.slideshow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labcortedosactividad.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SlideshowFragment extends Fragment {


    private SlideshowViewModel slideshowViewModel;

    private RecyclerView recyclerViewArchivo;
    private CustomAdapterArchivo customAdapterArchivo;
    View root;

    private List<String> horasArchivos;
    private String todoHorasArchivos = "";

    private final String archivoNombre = "LogRegistro.txt";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerViewArchivo= (RecyclerView) root.findViewById(R.id.RecyclerViewArchivo);
        recyclerViewArchivo.setLayoutManager(new LinearLayoutManager(root.getContext()));
        CardView cardView = (CardView) root.findViewById(R.id.cardViewArchivos);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                String archivos[] = root.getContext().fileList();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                String datetime = dateformat.format(c.getTime());
                if(encontrado(archivos,archivoNombre)){
                    leer();
                    guardar(datetime + " Ingreso registrado");
                }else{
                    guardar(datetime + " Ingreso registrado");
                }
                leer();
                customAdapterArchivo = new CustomAdapterArchivo(horasArchivos);
                recyclerViewArchivo.setAdapter(customAdapterArchivo);
            }
        });
        return root;
    }

    public void guardar(String contenido){
        contenido = todoHorasArchivos + contenido;
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(root.getContext().openFileOutput(archivoNombre, Activity.MODE_PRIVATE));
            archivo.write(contenido);
            todoHorasArchivos = "";
            archivo.flush();
            archivo.close();
        }catch (IOException e){
            System.out.println("Error al guardar en el archivo");
        }
    }

    private void leer(){
        horasArchivos = new ArrayList<>();
        try{
            InputStreamReader archivo = new InputStreamReader(root.getContext().openFileInput(archivoNombre));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();

            while(linea != null){
                horasArchivos.add(linea);
                todoHorasArchivos += linea +"\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();

        }catch (IOException e){
            System.out.println("Error al leer el archivo " + e);
        }
    }

    @SuppressLint("NewApi")
    private boolean encontrado(String archivos[], String  fichero){
        for (String archivo : archivos) {
            if (fichero.equals(archivo)) {
                return true;
            }
        }
        return  false;
    }
}
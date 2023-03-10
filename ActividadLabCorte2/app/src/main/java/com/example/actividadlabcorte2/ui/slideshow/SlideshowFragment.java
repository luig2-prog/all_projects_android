package com.example.actividadlabcorte2.ui.slideshow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private RecyclerView recyclerViewArchivo;
    private CustomAdapterArchivo customAdapterArchivo;
    private CardView cardView;

    private List<String> horasG;
    private String todo = "";

    private final String archivoNombre = "LogRegistro.txt";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
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

                if(encontrado(archivos,archivoNombre)   ){
                    leer(root);
                    guardar(datetime + " Ingreso registrado",root);
                }else{
                  guardar(datetime + " Ingreso registrado",root);
                }
                leer(root);
                customAdapterArchivo = new CustomAdapterArchivo(horasG);
                recyclerViewArchivo.setAdapter(customAdapterArchivo);
            }
        });

        return root;
    }

    public void guardar(String contenido, View root){
        contenido = todo + contenido;
        try {
           OutputStreamWriter archivo = new OutputStreamWriter(root.getContext().openFileOutput(archivoNombre, Activity.MODE_PRIVATE));
            archivo.write(contenido);
            todo = "";
            archivo.flush();
            archivo.close();

        }catch (IOException e){

        }
    }

    private void leer(View root){
        horasG = new ArrayList<>();
        try{
            InputStreamReader archivo = new InputStreamReader(root.getContext().openFileInput(archivoNombre));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();

            while(linea != null){
                horasG.add(linea);
                todo = todo + linea +"\n";
                linea = br.readLine();
            }
            //horasPrincipal = horas;
            br.close();
            archivo.close();

        }catch (IOException e){
            System.out.println("------------------- " + e);

        }


    }

    @SuppressLint("NewApi")
    private boolean encontrado(String archivos[], String  fichero){
        for ( int i =0; i < archivos.length ; i++ ){
            if(fichero.equals(archivos[i])){
                return true;
            }
        }
        return  false;
    }
}
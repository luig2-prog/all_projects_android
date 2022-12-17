package com.example.actividadlabcorte2.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.SQLlite.DatosSQL;
import com.example.actividadlabcorte2.SQLlite.RecursosBD;
import com.example.actividadlabcorte2.SQLlite.SQLmetodos;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {
    List<DatosSQL> datosSQLArrayList;
    RecyclerView recyclerView;
    SQLmetodos conn;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        conn = new SQLmetodos(root.getContext(),"bd_puestos",null,1);
        datosSQLArrayList= new ArrayList<>();
        recyclerView= root.findViewById(R.id.recyclersql);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        consultarpuestos();
        CustomAdapterSQL adaptadorsql=new CustomAdapterSQL(datosSQLArrayList);
        recyclerView.setAdapter(adaptadorsql);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


    private void consultarpuestos() {
        SQLiteDatabase db=conn.getReadableDatabase();
        DatosSQL datos=null;
        Cursor cursor=db.rawQuery("SELECT * FROM " + RecursosBD.TABLA_PUESTOS,null);
        while (cursor.moveToNext()){
            datos = new DatosSQL();
            datos.setDepa_nombre(cursor.getString(0));
            datos.setMuni_nombre(cursor.getString(1));
            datos.setSede_nombre(cursor.getString(2));
            datos.setDireccion(cursor.getString(3));
            datos.setTelefono(cursor.getString(4));
            datos.setEmail(cursor.getString(5));
            datos.setNaju_nombre(cursor.getString(6));
            datos.setFecha_corte_reps(cursor.getString(7));

            datosSQLArrayList.add(datos);
        }
    }

}
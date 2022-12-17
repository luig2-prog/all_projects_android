package com.example.actividadlabcorte2.ui.home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadlabcorte2.Interface.JsonPlaceHolderAPI;
import com.example.actividadlabcorte2.R;
import com.example.actividadlabcorte2.SQLlite.RecursosBD;
import com.example.actividadlabcorte2.SQLlite.SQLmetodos;
import com.example.actividadlabcorte2.ui.slideshow.CustomAdapterArchivo;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<Posts> postsList;
    private RecyclerView recyclerViewPosts;
    private CustomAdapterPosts customAdapterPosts;
    private CardView cardView;
    private final String URL = "https://www.datos.gov.co/resource/";
    SQLmetodos conn;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewPosts = (RecyclerView) root.findViewById(R.id.RecyclerViewPosts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(root.getContext()));
        conn = new SQLmetodos(root.getContext(),"bd_puestos",null,1);
        CardView cardView = (CardView) root.findViewById(R.id.cardViewPosts);
        //postsList = new ArrayList<>();
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL)
                        .build();

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
                Call<List<Posts>> call = jsonPlaceHolderAPI.getPost();

                call.enqueue(new Callback<List<Posts>>() {
                    @Override
                    public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(root.getContext(),"Codigo: " + response.code(),Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<Posts> objvacunacionList2 = response.body();
                        String content = "";
                        customAdapterPosts = new CustomAdapterPosts(objvacunacionList2,root.getContext(),HomeFragment.this::seleccion);
                        recyclerViewPosts.setAdapter(customAdapterPosts);

                    }

                    @Override
                    public void onFailure(Call<List<Posts>> call, Throwable t) {
                        System.out.println(t);
                    }
                });
            }
        });
        return root;
    }

    public void seleccion(Posts objvacunacion) {
        SQLiteDatabase db =conn.getWritableDatabase();
        SQLiteDatabase read=conn.getReadableDatabase();
        Cursor cursor=read.rawQuery("SELECT * FROM "+ RecursosBD.TABLA_PUESTOS+" WHERE "+RecursosBD.CAMPO_SEDE+" = "+ "'"+objvacunacion.getSede_nombre()+"'",null);
        if(cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(RecursosBD.CAMPO_DEPARTAMENTO,objvacunacion.getDepa_nombre());
            values.put(RecursosBD.CAMPO_MUNI,objvacunacion.getMuni_nombre());
            values.put(RecursosBD.CAMPO_SEDE,objvacunacion.getSede_nombre());
            values.put(RecursosBD.CAMPO_DIR,objvacunacion.getDireccion());
            values.put(RecursosBD.CAMPO_TEL,objvacunacion.getTelefono());
            values.put(RecursosBD.CAMPO_EMAIL,objvacunacion.getEmail());
            values.put(RecursosBD.CAMPO_naju_nombre,objvacunacion.getNaju_nombre());
            values.put(RecursosBD.CAMPO_fecha_corte_reps,objvacunacion.getFecha_corte_reps());
            Long idres=db.insert(RecursosBD.TABLA_PUESTOS,RecursosBD.CAMPO_ID,values);
            System.out.println("id registro: "+idres);
            Toast.makeText(root.getContext(),"Datos guardados satisfactoriamente",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(root.getContext(),"Error, el objeto fue guardado anteriormente",Toast.LENGTH_SHORT).show();
        }


    }
}
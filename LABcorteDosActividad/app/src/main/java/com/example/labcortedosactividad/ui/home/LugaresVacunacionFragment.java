package com.example.labcortedosactividad.ui.home;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labcortedosactividad.Interface.JsonPlaceHolderAPI;
import com.example.labcortedosactividad.R;
import com.example.labcortedosactividad.SQLlite.RecursosBD;
import com.example.labcortedosactividad.SQLlite.SQLmetodos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LugaresVacunacionFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewPosts;
    private CustomAdapterPosts customAdapterPosts;
    private CardView cardView;
    private final String URL = "https://www.datos.gov.co/resource/";
    SQLmetodos conn;
    View root;
    private ProgressBar progressBar;
    private  List<Posts> objvacunacionList2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        root = inflater.inflate(R.layout.fragment_lugares_vacunacion, container, false);

        recyclerViewPosts = (RecyclerView) root.findViewById(R.id.RecyclerViewPosts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(root.getContext()));
        conn = new SQLmetodos(root.getContext(),"bd_puestos",null,1);
        CardView cardView = (CardView) root.findViewById(R.id.cardViewPosts);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                objvacunacionList2 = new ArrayList<>();

                new Task3().execute(objvacunacionList2);

            }
        });
        return root;
    }

    private class Task3 extends AsyncTask<List<Posts>,Void,List<Posts>>{

        @Override
        protected List<Posts> doInBackground(List<Posts>... lists) {

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

                    objvacunacionList2 = response.body();
                    String content = "";
                    progressBar.setVisibility(View.INVISIBLE);
                    customAdapterPosts = new CustomAdapterPosts(objvacunacionList2,root.getContext(), LugaresVacunacionFragment.this::seleccion);
                    recyclerViewPosts.setAdapter(customAdapterPosts);

                }

                @Override
                public void onFailure(Call<List<Posts>> call, Throwable t) {
                    System.out.println(t);
                }
            });
            return objvacunacionList2;
        }
    }

    public void seleccion(Posts objvacunacion) {
        SQLiteDatabase db =conn.getWritableDatabase();
        SQLiteDatabase read=conn.getReadableDatabase();
        Cursor cursor=read.rawQuery("SELECT * FROM "+ RecursosBD.getTablaPuestos()+" WHERE "+RecursosBD.getCampSede()+" = "+ "'"+objvacunacion.getSede_nombre()+"'",null);
        if(cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(RecursosBD.getCampDepartamento(),objvacunacion.getDepa_nombre());
            values.put(RecursosBD.getCampMunicipio(),objvacunacion.getMuni_nombre());
            values.put(RecursosBD.getCampSede(),objvacunacion.getSede_nombre());
            values.put(RecursosBD.getCampDireccion(),objvacunacion.getDireccion());
            values.put(RecursosBD.getCampTelefono(),objvacunacion.getTelefono());
            values.put(RecursosBD.getCampEmail(),objvacunacion.getEmail());
            values.put(RecursosBD.getCampNajuNombre(),objvacunacion.getNaju_nombre());
            values.put(RecursosBD.getCampFechaCorte(),objvacunacion.getFecha_corte_reps());
            Long idres=db.insert(RecursosBD.getTablaPuestos(),RecursosBD.getCampId(),values);
            System.out.println("id registro: "+idres);
            Toast.makeText(root.getContext(),"Datos guardados satisfactoriamente",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(root.getContext(),"Error, el objeto fue guardado anteriormente",Toast.LENGTH_SHORT).show();
        }


    }
}
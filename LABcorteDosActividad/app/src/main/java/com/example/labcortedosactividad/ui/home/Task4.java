package com.example.labcortedosactividad.ui.home;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.labcortedosactividad.Interface.JsonPlaceHolderAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Task4 extends AsyncTask<List<Posts>,Void,List<Posts>> {
    private final String URL = "https://www.datos.gov.co/resource/";
    public List<Posts> objvacunacionList2 = new ArrayList<>();
    @Override
    protected void onPostExecute(List<Posts> posts) {
        super.onPostExecute(posts);
        System.out.println("OnPost");


    }

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
                    //Toast.makeText(root.getContext(),"Codigo: " + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                objvacunacionList2 = response.body();
                System.out.println(objvacunacionList2.size());
                String content = "";
                //progressBar.setVisibility(View.INVISIBLE);
                //customAdapterPosts = new CustomAdapterPosts(objvacunacionList2,root.getContext(),HomeFragment.this::seleccion);
                //recyclerViewPosts.setAdapter(customAdapterPosts);

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                System.out.println(t);
            }
        });
        return objvacunacionList2;
    }
}
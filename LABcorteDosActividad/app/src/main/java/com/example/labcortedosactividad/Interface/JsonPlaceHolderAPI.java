package com.example.labcortedosactividad.Interface;


import com.example.labcortedosactividad.ui.home.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("u82n-j82m.json")
    Call<List<Posts>> getPost();

}

package com.example.qrotuberapp.interfaces;

import com.example.qrotuberapp.models.Usuarios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioAPI {
    @POST("GetUsuarioPorId?")
    public Call<Usuarios> find(@Query("correo") String correo);
}

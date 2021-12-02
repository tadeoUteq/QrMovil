package com.example.qrotuberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrotuberapp.interfaces.UsuarioAPI;
import com.example.qrotuberapp.models.Usuarios;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    Button btnRegistrar;

    String correo;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

    }

    public void login(View view) {
        correo = etEmail.getText().toString();
        password = etPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/Videos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Se usa la interface creada
        UsuarioAPI usuarioAPI = retrofit.create(UsuarioAPI.class);
        Call<Usuarios> call = usuarioAPI.find(correo);
        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                Usuarios postUsuario = response.body();
                //String uncorreo = postUsuario.getCorreo();

                if(postUsuario != null){
                    if(postUsuario.getCorreo().equals(correo) && postUsuario.getPassword().equals(password)){
                        //Toast.makeText(getApplicationContext(), postUsuario.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SesionActivity.class)
                        .putExtra("correo", correo); // o postUsuario.getCorreo();
                        //intent.putExtra("id", postUsuario.getId());
                        //intent.putExtra("tipo", "1");
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Los datos no coinciden!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Los campos no deben estar vacios.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
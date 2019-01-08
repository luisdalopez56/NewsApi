package com.iesvirgendelcarmen.noticiasdeportes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tema1.luisdalopez56.proyectonoticias.R;

public class menu_Carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_carga);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(menu_Carga.this, menu_Principal.class);
                startActivity(intent);

            }
        },4000);

    }
}

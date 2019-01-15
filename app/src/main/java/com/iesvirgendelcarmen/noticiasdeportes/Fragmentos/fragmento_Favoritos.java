package com.iesvirgendelcarmen.noticiasdeportes.Fragmentos;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.iesvirgendelcarmen.noticiasdeportes.Adaptadores.NoticiasAdapter;
import com.iesvirgendelcarmen.noticiasdeportes.Noticias.NoticiasFavRetrofit;
import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.api.Callback;
import com.tema1.luisdalopez56.proyectonoticias.R;

import java.util.List;

public class fragmento_Favoritos extends android.app.Fragment {

    ListView listView;

    List<Noticia> listaNoticias;
    NoticiasAdapter adaptador;

    private Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback)
            this.callback = (Callback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_listanoticias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.noticias);

        NoticiasFavRetrofit.getInstance().getNoticias(new NoticiasFavRetrofit.CallbackNoticias() {
            @Override
            public void onNoticias(List<Noticia> noticias) {
                listaNoticias = noticias;
                System.out.println(listaNoticias);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    adaptador = new NoticiasAdapter(view.getContext(), noticias);
                }
                listView.setAdapter(adaptador);
            }

            @Override
            public void onNoticiasError(String mensajeError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.detectarNoticia(listaNoticias.get(position), true);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                NoticiasFavRetrofit.getInstance().deleteNoticia(listaNoticias.get(position), new NoticiasFavRetrofit.CallbackDeleteNoticia() {
                    @Override
                    public void onDeleteNoticia() {
                    }

                    @Override
                    public void onDeleteNoticiaError() {
                    }
                });

                listaNoticias.remove(position);
                adaptador.notifyDataSetChanged();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(getContext(), "ELIMINADO DE FAVORITOS", Toast.LENGTH_SHORT).show();
                }
                return false;

            }
        });
    }
}
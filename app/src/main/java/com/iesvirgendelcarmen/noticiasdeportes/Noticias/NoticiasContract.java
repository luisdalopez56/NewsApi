package com.iesvirgendelcarmen.noticiasdeportes.Noticias;

import android.content.Context;

import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Noticia;

import java.util.List;

public interface NoticiasContract {

    interface Vista {
        void mostrarNoticias(List<Noticia> noticias);
        void mostrarError();
    }

    interface Presentador {
        void cargarNoticias(Context context);
    }

}




package com.iesvirgendelcarmen.noticiasdeportes.NoticiasDetalle;

import com.iesvirgendelcarmen.noticiasdeportes.modelos.Noticia;

public interface NoticiasDetalleContract {

    interface Vista {
        void mostrarNoticia(Noticia noticia);
    }

    interface Presentador {
        void cargaNoticia(int posicion);

    }
}

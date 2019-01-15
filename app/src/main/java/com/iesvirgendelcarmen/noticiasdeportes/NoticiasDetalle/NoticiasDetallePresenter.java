package com.iesvirgendelcarmen.noticiasdeportes.NoticiasDetalle;

import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasRepositorio;

public class NoticiasDetallePresenter  implements NoticiasDetalleContract.Presentador{

    NoticiasDetalleContract.Vista vista;

    public NoticiasDetallePresenter(NoticiasDetalleContract.Vista vista) {
        this.vista = vista;
    }


    @Override
    public void cargaNoticia(int posicion) {
        Noticia noticia = NoticiasRepositorio.getInstance().getNoticia(posicion);
        vista.mostrarNoticia(noticia);

    }
}

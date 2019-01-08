package com.iesvirgendelcarmen.noticiasdeportes.modelos;

import android.content.Context;

import com.iesvirgendelcarmen.noticiasdeportes.modelos.api.NewsApi;

import java.util.ArrayList;
import java.util.List;

public class NoticiasRepositorio {

    private static NoticiasRepositorio instance;
    List<Noticia> noticiaList;
    ArrayList<Proveedor> proveedorList;
    String proveedorSeleccionado;
    int noticiaSeleccionada;

    private NoticiasRepositorio() {

    }

    public List<Noticia> getNoticiaList() {
        return noticiaList;
    }

    public static NoticiasRepositorio getInstance() {
        if (instance==null) {
            instance = new NoticiasRepositorio();
        }
        return instance;
    }

    public String getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(String proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public int getNoticiaSeleccionada() {
        return noticiaSeleccionada;
    }

    public void setNoticiaSeleccionada(int noticiaSeleccionada) {
        this.noticiaSeleccionada = noticiaSeleccionada;
    }

    public void cargarNoticias(Context context, final Callback callback) {

        if (noticiaList == null) {

            NewsApi newsApi = new NewsApi();
            newsApi.ultimasNoticias(context, new NewsApi.Callback() {
                @Override
                public void getLista(List<Noticia> noticias) {
                    noticiaList = noticias;
                    callback.onNoticiasCargadas(noticias);
                }

                @Override
                public void onError() {
                    noticiaList = null;
                }
            });
        }
        else {
            callback.onNoticiasCargadas(noticiaList);
        }
    }

    public static void setInstance(NoticiasRepositorio instance) {
        NoticiasRepositorio.instance = instance;
    }

    public void recargarNoticias(){

        if (noticiaList != null) {
            noticiaList.clear();
            setInstance(null);
        }

    }

    public Noticia getNoticia(int posicion) {
        if (noticiaList!=null)
            return noticiaList.get(posicion);
        else
            return null;
    }

    public Proveedor getProveedor(int position){
        return proveedorList.get(position);
    }

    public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
    }

    public interface Callback {
        void onNoticiasCargadas(List<Noticia> noticias);
    }


}

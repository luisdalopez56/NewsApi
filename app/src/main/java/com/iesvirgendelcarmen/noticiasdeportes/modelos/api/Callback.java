package com.iesvirgendelcarmen.noticiasdeportes.modelos.api;


import com.iesvirgendelcarmen.noticiasdeportes.modelos.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.Proveedor;

public interface Callback {
    void detectarNoticia(Noticia noticia, Boolean estado);
}

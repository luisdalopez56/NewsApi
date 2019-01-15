package com.iesvirgendelcarmen.noticiasdeportes.modelos.api;


import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Noticia;

public interface Callback {
    void detectarNoticia(Noticia noticia, Boolean estado);
}

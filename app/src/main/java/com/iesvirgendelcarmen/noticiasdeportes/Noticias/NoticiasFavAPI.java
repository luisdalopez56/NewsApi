package com.iesvirgendelcarmen.noticiasdeportes.Noticias;

import com.iesvirgendelcarmen.noticiasdeportes.modelos.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NoticiasFavAPI {

    @POST("noticias")
    public Call<Noticia> postNoticias(@Body Noticia noticia);

    @GET("noticias")
    public Call<List<Noticia>> getNoticias();

    @DELETE("noticias/{id}")
    public Call<Noticia> deleteNoticia(@Path("id") String id);
}
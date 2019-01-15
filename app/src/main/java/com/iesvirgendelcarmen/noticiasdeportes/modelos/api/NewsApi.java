package com.iesvirgendelcarmen.noticiasdeportes.modelos.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasRepositorio;

import java.io.StringReader;
import java.util.List;

public class NewsApi {

    private String ENDPOINT_TODO = "https://newsapi.org/v2/everything?sources=%s&apiKey=%s";
    private String SOURCE = NoticiasRepositorio.getInstance().getProveedorSeleccionado();
    private String APIKEY = "e9e3cbfdc42c4ab295a4cac9f7247401";
    private String url;
    public NewsApi() {
        this.url = String.format(ENDPOINT_TODO, SOURCE, APIKEY);
    }

    public void ultimasNoticias(Context ctx, final Callback respuesta) {

        RequestQueue req = VolleySingleton.getInstance(ctx).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Respuesta", response);
                        JsonParser parser = new JsonParser();
                        JsonElement e = parser.parse(response);
                        JsonElement articulos = e.getAsJsonObject().get("articles");
                        Log.i("Articulos", articulos.toString());
                        List<Noticia> noticias = new Gson().fromJson(new StringReader(articulos.toString()), new TypeToken<List<Noticia>>() {
                        }.getType());
                        respuesta.getLista(noticias);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        respuesta.onError();
                    }
                });

        req.add(stringRequest);
    }

    public interface Callback {
        void getLista(List<Noticia> noticias);
        void onError();
    }

}

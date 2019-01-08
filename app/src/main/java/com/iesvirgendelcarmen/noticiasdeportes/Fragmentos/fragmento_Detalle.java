package com.iesvirgendelcarmen.noticiasdeportes.Fragmentos;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.iesvirgendelcarmen.noticiasdeportes.NoticiasDetalle.NoticiasDetalleContract;
import com.iesvirgendelcarmen.noticiasdeportes.NoticiasDetalle.NoticiasDetallePresenter;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasRepositorio;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.api.VolleySingleton;
import com.tema1.luisdalopez56.proyectonoticias.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class fragmento_Detalle extends Fragment implements NoticiasDetalleContract.Vista {

    @BindView(R.id.detalletitulo)
    TextView titulo;
    @BindView(R.id.detalleAutor)
    TextView autor;
    @BindView(R.id.detalleDescripcion)
    TextView descripcion;
    @BindView(R.id.detalleFecha)
    TextView fecha;
    @BindView(R.id.detalleImagen)
    NetworkImageView imagen;

    NoticiasDetalleContract.Presentador presentador;
    List<Noticia> listaNoticias;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        presentador = new NoticiasDetallePresenter(this);
        return inflater.inflate(R.layout.fragmento_noticias_detalle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);


        int posicion = NoticiasRepositorio.getInstance().getNoticiaSeleccionada();

        listaNoticias = NoticiasRepositorio.getInstance().getNoticiaList();

        presentador.cargaNoticia(posicion);

        Noticia noticia = listaNoticias.get(posicion);

        mostrarNoticia(noticia);
    }

    @Override
    public void mostrarNoticia(Noticia noticia) {
        titulo.setText(noticia.getTitle());
        autor.setText(noticia.getAuthor());
        descripcion.setText(noticia.getDescription());
        fecha.setText((noticia.getPublishedAt()+""));
        imagen.setImageUrl(noticia.getUrlToImage(), VolleySingleton.getInstance(getActivity()).getImageLoader());    }
}
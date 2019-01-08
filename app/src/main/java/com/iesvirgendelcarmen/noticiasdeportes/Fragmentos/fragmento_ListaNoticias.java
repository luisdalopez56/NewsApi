package com.iesvirgendelcarmen.noticiasdeportes.Fragmentos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.iesvirgendelcarmen.noticiasdeportes.Noticias.NoticiasContract;
import com.iesvirgendelcarmen.noticiasdeportes.Noticias.NoticiasPresenter;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.Noticia;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasAdapter;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasRepositorio;
import com.tema1.luisdalopez56.proyectonoticias.R;
import java.util.List;
import butterknife.ButterKnife;

public class fragmento_ListaNoticias extends Fragment implements NoticiasContract.Vista {

    public static final String EXTRA_NOTICIA = "EXTRA_NOTICIA";

    ListView listViewNoticias;

    private NoticiasContract.Presentador presenter;

    private List<Noticia> listaNoticias;
    private NoticiasAdapter noticiasAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_listanoticias,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        listViewNoticias = view.findViewById(R.id.noticias);
        presenter = new NoticiasPresenter(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            presenter.cargarNoticias(getContext());
        }

        setLayout();
    }



    private void setLayout() {

        //Evento click en item de la lista
        listViewNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NoticiasRepositorio.getInstance().setNoticiaSeleccionada(i);

                fragmento_Detalle fragment = new fragmento_Detalle();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmento, fragment).addToBackStack(null);
                ft.commit();


            }
        });
    }


    @Override
    public void mostrarNoticias(List<Noticia> noticias) {
        listaNoticias = noticias;
        NoticiasAdapter adapter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            adapter = new NoticiasAdapter(getContext(), noticias);
        }
        listViewNoticias.setAdapter(adapter);
    }

    @Override
    public void mostrarError() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(getContext(), "Error al descargar noticias",Toast.LENGTH_LONG).show();
        }
    }
}

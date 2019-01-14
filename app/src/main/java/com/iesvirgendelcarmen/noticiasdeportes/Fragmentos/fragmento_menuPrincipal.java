package com.iesvirgendelcarmen.noticiasdeportes.Fragmentos;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.iesvirgendelcarmen.noticiasdeportes.modelos.DesplegableAdapter;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.NoticiasRepositorio;
import com.iesvirgendelcarmen.noticiasdeportes.modelos.Proveedor;
import com.tema1.luisdalopez56.proyectonoticias.R;

import java.util.ArrayList;

public class fragmento_menuPrincipal extends Fragment {

    ArrayList<Proveedor> listaProveedores;
    private DesplegableAdapter spinnerAdapter;

    Spinner spinner;
    Button botonCargar, botonAbrirFavoritos;

    ListView lista;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_menuprincipal,container,false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList();

        spinner = view.findViewById(R.id.spinner);
        lista = view.findViewById(R.id.noticias);
        spinnerAdapter = new DesplegableAdapter(view.getContext(),listaProveedores);
        spinner.setAdapter(spinnerAdapter);


        botonCargar = view.findViewById(R.id.btn_cargarNoticias);
        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticiasRepositorio.getInstance().recargarNoticias();
                cargarProveedor();

            }
        });

        botonAbrirFavoritos = view.findViewById(R.id.btn_abrirFavoritos);
        botonAbrirFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmento_Favoritos fragmento = new fragmento_Favoritos();
                abrirFavoritos(fragmento);
            }
        });


    }

    @SuppressLint("ResourceType")
    private void cargarProveedor() {
        fragmento_ListaNoticias fragment = new fragmento_ListaNoticias();
        NoticiasRepositorio.getInstance().setProveedorSeleccionado(spinner.getSelectedItem().toString());
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmento, fragment).addToBackStack(null);
        ft.commit();

    }

    public void abrirFavoritos(fragmento_Favoritos fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmento, fragment).addToBackStack(null);
        ft.commit();
    }

    private void initList() {
        listaProveedores = new ArrayList<>();
        listaProveedores.add(new Proveedor("Marca",R.drawable.marca_logo,"marca"));
        listaProveedores.add(new Proveedor("El Mundo",R.drawable.elmundo_logo,"el-mundo"));
        listaProveedores.add(new Proveedor("CNN",R.drawable.cnn_logo,"cnn-es"));

        NoticiasRepositorio.getInstance().setListaProveedores(listaProveedores);
    }
}

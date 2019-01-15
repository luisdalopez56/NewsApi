package com.iesvirgendelcarmen.noticiasdeportes.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesvirgendelcarmen.noticiasdeportes.Modelo.Proveedor;
import com.tema1.luisdalopez56.proyectonoticias.R;

import java.util.ArrayList;

public class DesplegableAdapter extends ArrayAdapter {

    ImageView imagenProveedor;
    TextView nombreProveedor;

    public DesplegableAdapter(Context context, ArrayList<Proveedor> listaProveedores){
        super(context, 0, listaProveedores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.celdaspinner,parent,false);
        }
        imagenProveedor = convertView.findViewById(R.id.imagenProveedor);
        nombreProveedor = convertView.findViewById(R.id.nombreProveedor);

        Proveedor proveedor = (Proveedor) getItem(position);

        if (parent != null) {
            imagenProveedor.setImageResource(proveedor.getImagen());
            nombreProveedor.setText(proveedor.getNombre());
        }

        return convertView;
    }
}

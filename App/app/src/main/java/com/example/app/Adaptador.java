package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Usuario> lista;
    FuncionUsuario fun;
    Usuario u;
    Activity a;

    public Adaptador(ArrayList<Usuario> lista, Activity a, FuncionUsuario fun) {
        this.lista = lista;
        this.a = a;
        this.fun = fun;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Usuario getItem(int i) {
        u=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        u=lista.get(i);
        return u.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v=view;
        if (v!=null){
            LayoutInflater li=(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.item,null);
        }
        u=lista.get(posicion);
        TextView nombre=(TextView)v.findViewById(R.id.t_nombre);
        TextView telefono=(TextView)v.findViewById(R.id.t_telefono);
        TextView email=(TextView)v.findViewById(R.id.t_email);
        TextView edad=(TextView)v.findViewById(R.id.t_edad);
        Button editar=(Button)v.findViewById(R.id.editar);
        Button eliminar=(Button)v.findViewById(R.id.eliminar);
        nombre.setText(u.getNombre());
        telefono.setText(u.getTelefono());
        email.setText(u.getEmail());
        edad.setText(u.getEdad());
        return v;
    }
}

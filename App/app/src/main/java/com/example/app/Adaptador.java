package com.example.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Usuario> lista;
    FuncionUsuario fun;
    Usuario u;
    Activity a;
    int id=0;

    public Adaptador(ArrayList<Usuario> lista, Activity a, FuncionUsuario fun) {
        this.lista = lista;
        this.a = a;
        this.fun = fun;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (v==null){
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
        edad.setText(""+u.getEdad());
        editar.setTag(posicion);
        eliminar.setTag(posicion);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EDITAR
                int pos=Integer.parseInt(view.getTag().toString());
                final Dialog dialogo= new Dialog(a);
                dialogo.setTitle("Editar registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();
                final EditText nombre=(EditText)dialogo.findViewById(R.id.nombre);
                final EditText telefono=(EditText)dialogo.findViewById(R.id.telefono);
                final EditText email=(EditText)dialogo.findViewById(R.id.email);
                final EditText edad=(EditText)dialogo.findViewById(R.id.edad);
                Button guardar=(Button) dialogo.findViewById(R.id.d_agregar);
                guardar.setText("Guardar");
                Button cancelar=(Button) dialogo.findViewById(R.id.d_cancelar);
                u=lista.get(pos);
                setId(u.getId());
                nombre.setText(u.getNombre());
                telefono.setText(u.getTelefono());
                email.setText(u.getEmail());
                edad.setText(""+u.getEdad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            u=new Usuario(nombre.getText() . toString(),
                                    telefono.getText() .toString(),
                                    email.getText() . toString(),
                                    Integer.parseInt(edad.getText() . toString())
                            );
                            fun.editar(u,getId());
                            lista=fun.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();


                        }catch (Exception u){
                            Toast.makeText(a,"ERROR",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CONFIRMAR SI NO
                int pos=Integer.parseInt(view.getTag().toString());
                u=lista.get(pos);
                setId(u.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage("¿Esta seguro de querer eliminar el Usuario?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fun.eliminar(getId());
                        lista=fun.verTodos();
                        notifyDataSetChanged();

                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();


            }
        });
        return v;
    }
}

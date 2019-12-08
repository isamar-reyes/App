package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class FuncionUsuario {

    SQLiteDatabase cx;
    ArrayList<Usuario>lista=new ArrayList<Usuario>();
    Usuario u;
    Context ct;
    String nombreBD="BDUsuario";
    String tabla= "create table if not exists usuario(id integer primary key autoincrement, nombre text, telefono text, email text, edad integer)";

    public FuncionUsuario(Context c){
        this.ct=c;
        cx=c.openOrCreateDatabase(nombreBD,Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);

    }
    public boolean insertar(Usuario u){
        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",u.getNombre());
        contenedor.put("telefono",u.getTelefono());
        contenedor.put("email",u.getEmail());
        contenedor.put("edad",u.getEdad());

        return (cx.insert("usuario",null,contenedor))>0;


    }
    public boolean eliminar(int id){

        return (cx.delete("usuario","id="+id,null))>0;
    }
    public boolean editar(Usuario u, int id){

        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",u.getNombre());
        contenedor.put("telefono",u.getTelefono());
        contenedor.put("email",u.getEmail());
        contenedor.put("edad",u.getEdad());

        return (cx.update("usuario",contenedor,"id="+id,null))>0;

    }

    public ArrayList<Usuario>verTodos(){
        lista.clear();
        Cursor cursor=cx.rawQuery("select * from usuario",null);
        if (cursor!=null &&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
              lista.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        return lista;

    }
    public Usuario verUno(int posicion){
        Cursor cursor=cx.rawQuery("select * from contacto",null);
        cursor.moveToPosition(posicion);
        u=new Usuario(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4));
        return u;

    }

}

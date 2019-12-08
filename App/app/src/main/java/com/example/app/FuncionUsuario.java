package com.example.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDatabase;
import java.util.ArrayList;

public class FuncionUsuario {

    SQLiteDatabase cx;
    ArrayList<Usuario>lista;
    Usuario u;
    Context ct;
    String nombreBD="BDUsuario";
    String tabla= "create table if not exists usuario(id interger primary key autoincrement, nombre text, telefono text, email text, edad interger)";

    public FuncionUsuario(Context c){
        this.ct=c;
        cx=c.openOrCreateDatabase(nombreBD,Context.MODE_WORLD_WRITEABLE, cursorFactory:null);
        cx.execSQL(tabla);

    }
    public boolean insertar(Usuario u){
        return true;

    }
    public boolean eliminar(int id){
        return true;

    }
    public boolean editar(Usuario u){
        return true;

    }

    public ArrayList<Usuario>verTodos(){
        return lista;

    }
    public Usuario verUno(int id){
        return c;

    }

}

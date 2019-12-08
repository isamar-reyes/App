package com.example.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class MainCRUD extends AppCompatActivity {
    FuncionUsuario fun;
    Adaptador adapter;
    ArrayList<Usuario> lista;
    Usuario u;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.crud);
        fun= new FuncionUsuario(this);
        lista=fun.verTodos();
        adapter=new Adaptador(lista,this,fun);
        ListView list = (ListView)findViewById(R.id.lista);
        Button agregar=(Button)findViewById(R.id.agregar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //VISTA PREVIA REGISTRO VISTA.XML
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DIALOGO.XML
                final Dialog dialogo= new Dialog(MainCRUD.this);
                dialogo.setTitle("Nuevo registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();
                final EditText nombre=(EditText)dialogo.findViewById(R.id.nombre);
                final EditText telefono=(EditText)dialogo.findViewById(R.id.telefono);
                final EditText email=(EditText)dialogo.findViewById(R.id.email);
                final EditText edad=(EditText)dialogo.findViewById(R.id.edad);
                Button guardar=(Button) dialogo.findViewById(R.id.d_agregar);
                guardar.setText("agregar");
                Button cancelar=(Button) dialogo.findViewById(R.id.d_cancelar);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            u=new Usuario(nombre.getText() . toString(),
                                          telefono.getText() .toString(),
                                          email.getText() . toString(),
                                          Integer.parseInt(edad.getText() . toString())
                                          );
                             fun.insertar(u);
                             lista=fun.verTodos();
                             adapter.notifyDataSetChanged();
                             dialogo.dismiss();


                        }catch (Exception u){
                            Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
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


    }
}

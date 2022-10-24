package com.example.tarea_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tarea_3.configuracion.Conexion;
import com.example.tarea_3.tablas.personas;
import com.example.tarea_3.tablas.transacs;

import java.util.ArrayList;

public class View_Peoples_Activity extends AppCompatActivity {

    ListView listPerson;
    Button btnMod, btnDel, btnView;
    private personas personas;
    Conexion con;
    ArrayList<personas> Plist;
    ArrayList<String> listaConcat;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_peoples);
        chargeObj();
        con = new Conexion(this,transacs.dbName,null,1);
        getListP();

        ArrayAdapter adp =new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaConcat);
        listPerson.setAdapter(adp);

        listPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fillPerson(i);
                id = personas.getId();
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_People_Activity.class);
                if(personas != null){
                    Add_People_Activity.id =  personas.getId();
                    Add_People_Activity.nombres = personas.getNombres();
                    Add_People_Activity.apellidos = personas.getApellidos();
                    Add_People_Activity.edad = personas.getEdad();
                    Add_People_Activity.correo = personas.getCorreo();
                    Add_People_Activity.direccion = personas.getDireccion();
                    Add_People_Activity.isUpdt = true;
                    startActivity(intent);
                } else Toast.makeText(View_Peoples_Activity.this, "!!!!Alerta " + "\n No ha seleccionado ningun campo", Toast.LENGTH_SHORT).show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personas != null) delP();
                else Toast.makeText(View_Peoples_Activity.this, "!!!!Alerta " + "\n No ha seleccionado ningun campo", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(personas != null)
            }
        });
    }

    private void getListP(){
        try{
            SQLiteDatabase db = con.getReadableDatabase();
            personas p = null;

            Plist  =new ArrayList<personas>();
            Cursor cursor =db.rawQuery(transacs.getTblPersona, null);

            while (cursor.moveToNext()){
                p = new personas();
                p.setId(cursor.getInt(0));
                p.setNombres(cursor.getString(1));
                p.setApellidos(cursor.getString(2));
                p.setEdad(cursor.getInt(3));
                p.setCorreo(cursor.getString(4));
                p.setDireccion(cursor.getString(5));

                Plist.add(p);
            }
            cursor.close();
            fillList();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString() + " error");
        }
    }

    private void fillList(){
        listaConcat = new ArrayList<String>();

        for(int i=0; i<Plist.size(); i++){
            listaConcat.add(Plist.get(i).getNombres() + " - "
                    + Plist.get(i).getApellidos() + " - "
                    + Plist.get(i).getEdad());
        }
    }

    private void delP(){
        try{
            Conexion con = new Conexion(this, transacs.dbName,null,1);
            SQLiteDatabase bd = con.getWritableDatabase();


            String[] args = new String[]{Integer.toString(id)};

            Long result = Long.valueOf(bd.delete(transacs.tblPersonas, "id = ?", args));

            Toast.makeText(this, "!!!Aviso " + "\n Contacto Eliminado" + result.toString(), Toast.LENGTH_SHORT).show();
            bd.close();
            updtList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updtList(){
        Plist.clear();
        listaConcat.clear();
        getListP();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaConcat);
        listPerson.setAdapter(adp);
    }

    private void fillPerson(int i){
        personas = new personas();
        personas.setId(Plist.get(i).getId());
        personas.setNombres(Plist.get(i).getNombres());
        personas.setApellidos(Plist.get(i).getApellidos());
        personas.setEdad(Plist.get(i).getEdad());
        personas.setCorreo(Plist.get(i).getCorreo());
        personas.setDireccion(Plist.get(i).getDireccion());
    }

    private void chargeObj(){
        listPerson = (ListView) findViewById(R.id.listPerson);
        btnMod = (Button) findViewById(R.id.btnMod);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnView = (Button) findViewById(R.id.btnView);
    }
}
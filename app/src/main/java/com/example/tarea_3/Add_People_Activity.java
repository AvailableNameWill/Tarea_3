package com.example.tarea_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea_3.configuracion.Conexion;
import com.example.tarea_3.tablas.personas;
import com.example.tarea_3.tablas.transacs;

public class Add_People_Activity extends AppCompatActivity {

    private EditText txtNamesM, txtSnamesM, txtAgeM, txtEmailM, txtDirM;
    private Button btnSaveM, btnListP;
    public static personas personas;
    public static Integer id=0, edad=0;
    public static String nombres="", apellidos="", correo="", direccion="";
    public static boolean isUpdt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        personas = new personas();
        chargeObj();

        btnSaveM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty = isEmpty();
                if(!empty){
                    if(!isUpdt) agrPersona();
                    else{
                        updtPersonas();
                        isUpdt = false;
                    }
                }
            }
        });

        btnListP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),View_Peoples_Activity.class);
                isUpdt = false;
                startActivity(intent);
            }
        });
    }

    private void chargeObj(){
        txtNamesM  = (EditText) findViewById(R.id.txtNamesM);
        txtNamesM.setText(nombres);
        InputFilter[] filter1 = new InputFilter[1];
        filter1[0] = new InputFilter.LengthFilter(50);
        txtNamesM.setFilters(filter1);
        txtSnamesM  = (EditText) findViewById(R.id.txtSnamesM);
        txtSnamesM.setText(apellidos);
        InputFilter[] filter2 = new InputFilter[1];
        filter2[0] = new InputFilter.LengthFilter(50);
        txtSnamesM.setFilters(filter2);
        txtAgeM  = (EditText) findViewById(R.id.txtAgeM);
        txtAgeM.setText(Integer.toString(edad));
        InputFilter[] filter3 = new InputFilter[1];
        filter3[0] = new InputFilter.LengthFilter(50);
        txtAgeM.setFilters(filter3);
        txtEmailM  = (EditText) findViewById(R.id.txtEmailM);
        txtEmailM.setText(correo);
        InputFilter[] filter4 = new InputFilter[1];
        filter4[0] = new InputFilter.LengthFilter(50);
        txtEmailM.setFilters(filter4);
        txtDirM  = (EditText) findViewById(R.id.txtDirM);
        txtDirM.setText(direccion);
        InputFilter[] filter5 = new InputFilter[1];
        filter5[0] = new InputFilter.LengthFilter(50);
        txtDirM.setFilters(filter5);
        btnSaveM  = (Button) findViewById(R.id.btnSaveM);
        btnListP = (Button) findViewById(R.id.btnListP);
    }

    private void agrPersona(){
        try{
            Conexion con = new Conexion(this, transacs.dbName, null, 1);
            SQLiteDatabase db = con.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(transacs.nombres, txtNamesM.getText().toString());
            cv.put(transacs.apellidos, txtSnamesM.getText().toString());
            cv.put(transacs.edad, txtAgeM.getText().toString());
            cv.put(transacs.correo, txtEmailM.getText().toString());
            cv.put(transacs.direccion, txtDirM.getText().toString());

            Long result =db.insert(transacs.tblPersonas, transacs.id, cv);
            Toast.makeText(this, "Registro ingresado correctamente", Toast.LENGTH_SHORT).show();
            db.close();
            clean();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updtPersonas(){
        try{
            Conexion con = new Conexion(this, transacs.dbName,null,1);
            SQLiteDatabase bd = con.getWritableDatabase();

            ContentValues values =new ContentValues();
            values.put(transacs.id, personas.getId());
            values.put(transacs.nombres, txtNamesM.getText().toString());
            values.put(transacs.apellidos, txtSnamesM.getText().toString());
            values.put(transacs.edad, txtAgeM.getText().toString());
            values.put(transacs.correo, txtEmailM.getText().toString());
            values.put(transacs.direccion, txtDirM.getText().toString());

            String[] args = new String[]{Integer.toString(id)};

            Long result = Long.valueOf(bd.update(transacs.tblPersonas, values, "id = ?", args));

            Toast.makeText(this, "Registro ingresado correctamente" + result.toString(), Toast.LENGTH_SHORT).show();
            bd.close();
            clean();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clean(){
        txtNamesM.setText("");
        txtSnamesM.setText("");
        txtAgeM.setText("");
        txtEmailM.setText("");
        txtDirM.setText("");
    }

    private boolean isEmpty(){
        if(txtNamesM.getText().toString().isEmpty()){
            Toast.makeText(Add_People_Activity.this, "!!!Alerta " + "\n no puede dejar el campo de texto vacio: Nombres", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(txtSnamesM.getText().toString().isEmpty()){
            Toast.makeText(Add_People_Activity.this, "!!!Alerta " + "\n no puede dejar el campo de texto vacio: Apellidos", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(txtAgeM.getText().toString().isEmpty()){
            Toast.makeText(Add_People_Activity.this, "!!!Alerta " + "\n no puede dejar el campo de texto vacio: Edad", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(txtEmailM.getText().toString().isEmpty()){
            Toast.makeText(Add_People_Activity.this, "!!!Alerta " + "\n no puede dejar el campo de texto vacio: Email", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(txtDirM.getText().toString().isEmpty()){
            Toast.makeText(Add_People_Activity.this, "!!!Alerta " + "\n no puede dejar el campo de texto vacio: Direccion", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
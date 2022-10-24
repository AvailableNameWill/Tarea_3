package com.example.tarea_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.tarea_3.tablas.personas;

public class View_People_Activity extends AppCompatActivity {

    public static personas personas = new personas();
    EditText txtNamesV, txtApelsV, txtAgeV, txtEmailV, txtDirV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_people);
        chargeObj();
    }

    private void chargeObj(){
        txtNamesV = (EditText) findViewById(R.id.txtNmaesV);
        txtNamesV.setText(personas.getNombres());
        txtApelsV = (EditText) findViewById(R.id.txtSnamesV);
        txtApelsV.setText(personas.getApellidos());
        txtAgeV = (EditText) findViewById(R.id.txtAgeV);
        txtAgeV.setText(personas.getEdad().toString());
        txtEmailV = (EditText) findViewById(R.id.txtEmailV);
        txtEmailV.setText(personas.getCorreo());
        txtDirV = (EditText) findViewById(R.id.txtDirV);
        txtDirV.setText(personas.getDireccion());
    }
}
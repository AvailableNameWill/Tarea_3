package com.example.tarea_3.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tarea_3.tablas.transacs;

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String dbName,SQLiteDatabase.CursorFactory factory, int version){
        super(context,dbName,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(transacs.crearTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(transacs.DropTblPersona);
        onCreate(db);
    }


}

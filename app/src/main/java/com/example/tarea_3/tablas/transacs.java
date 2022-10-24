package com.example.tarea_3.tablas;

public class transacs {
    public static final String dbName = "tarea_3";

    public static final String tblPersonas= "personas";

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    public static final String crearTbl = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT,"
                  + "apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String getTblPersona = "SELECT * FROM " + transacs.tblPersonas;

    public static final String DropTblPersona= "DROP TABLE IF EXISTS personas";
}

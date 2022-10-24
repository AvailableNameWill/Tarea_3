package com.example.tarea_3.tablas;

public class personas {
    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String correo;
    private String direccion;

    public personas(){}

    public personas(Integer _id, String  _nombres, String _apellidos, Integer _edad, String _correo, String _direccion){
        id = _id;
        nombres = _nombres;
        apellidos = _apellidos;
        edad = _edad;
        correo = _correo;
        direccion = _direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

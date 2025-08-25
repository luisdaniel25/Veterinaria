package modelo;

public class Usuario {
// Atributos de la clase

    private int id_usuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private int id_rol;
    private int id_especialidad;

    //Constructor vacio
    public Usuario() {
    }

    //constructor con parametros
    public Usuario(int id_usuario, String nombre, String correo, String contrasena, int id_rol, int id_especialidad) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.id_rol = id_rol;
        this.id_especialidad = id_especialidad;
    }

    //Getter AND Setter
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getRol() {
        return id_rol;
    }

    public void setRol(int rol) {
        this.id_rol = id_rol;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

}

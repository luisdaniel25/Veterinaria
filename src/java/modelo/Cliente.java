package modelo;

import java.util.Date;

public class Cliente {

    private int id_cliente;
    private String cedula_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    // 🔹 Constructor vacío
    public Cliente() {
    }

    // 🔹 Constructor con todos los campos
    public Cliente(int id_cliente, String cedula_cliente, String nombre, String apellido, String direccion, String telefono) {
        this.id_cliente = id_cliente;
        this.cedula_cliente = cedula_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    // 🔹 Constructor sin id (para inserciones)
    public Cliente(String cedula_cliente, String nombre, String apellido, String direccion, String telefono) {
        this.cedula_cliente = cedula_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;

    }

    @Override
    public String toString() {
        return "Cliente{"
                + "id_cliente=" + id_cliente
                + ", cedula_cliente='" + cedula_cliente + '\''
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", direccion='" + direccion + '\''
                + ", telefono='" + telefono + '\''
                + '}';
    }
}

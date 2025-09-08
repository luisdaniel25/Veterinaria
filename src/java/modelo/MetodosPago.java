package modelo;

public class MetodosPago {

    private int id_metodo;
    private String nombre;
    private String descripcion;

    // 🔹 Constructor vacío
    public MetodosPago() {
    }

    // 🔹 Constructor completo
    public MetodosPago(int id_metodo, String nombre, String descripcion) {
        this.id_metodo = id_metodo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // 🔹 Constructor sin id (para inserciones)
    public MetodosPago(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId_metodo() {
        return id_metodo;
    }

    public void setId_metodo(int id_metodo) {
        this.id_metodo = id_metodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MetodosPago{"
                + "id_metodo=" + id_metodo
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + '}';
    }
}

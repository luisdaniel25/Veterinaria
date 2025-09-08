package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Productos {

    private int id_producto;
    private String codigo_barras;
    private String nombre;
    private String marca;
    private BigDecimal precio;
    private int id_proveedor;

    public Productos() {
    }

    public Productos(int id_producto, String codigo_barras, String nombre, String marca, BigDecimal precio, int id_proveedor) {
        this.id_producto = id_producto;
        this.codigo_barras = codigo_barras;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    @Override
    public String toString() {
        return "Productos{" + "id_producto=" + id_producto + ", codigo_barras=" + codigo_barras + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", id_proveedor=" + id_proveedor + '}';
    }

}

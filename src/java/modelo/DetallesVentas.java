package modelo;

import java.math.BigDecimal;

public class DetallesVentas {

    private int id_detalle;
    private int id_venta;
    private int id_producto;
    private int cantidad;
    private BigDecimal precio;

    public DetallesVentas() {
    }

    // Constructor completo
    public DetallesVentas(int id_detalle, int id_venta, int id_producto, int cantidad, BigDecimal precio) {
        this.id_detalle = id_detalle;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Constructor usado en FacturaServlet
    public DetallesVentas(int id_producto, int cantidad, BigDecimal precio) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y setters
    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetallesVentas{"
                + "id_detalle=" + id_detalle
                + ", id_venta=" + id_venta
                + ", id_producto=" + id_producto
                + ", cantidad=" + cantidad
                + ", precio=" + precio
                + '}';
    }
}

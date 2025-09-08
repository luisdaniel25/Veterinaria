package modelo;

import java.time.LocalDateTime;

public class Bitacora {

    private int id_log;
    private String accion;
    private String tabla_afectada;
    private String id_referencia;
    private String detalle;
    private int id_usuario;
    private TipoOperacion tipo_operacion; // ENUM en Java
    private LocalDateTime fecha_hora;
    private String modulo;
    private String estado_previos;
    private String estado_nuevo;
    private String ip_usuario;

    // Constructor vacío (necesario para frameworks y JDBC)
    public Bitacora() {
    }

    // Constructor completo
    public Bitacora(int id_log, String accion, String tabla_afectada, String id_referencia,
            String detalle, int id_usuario, TipoOperacion tipo_operacion,
            LocalDateTime fecha_hora, String modulo,
            String estado_previos, String estado_nuevo, String ip_usuario) {
        this.id_log = id_log;
        this.accion = accion;
        this.tabla_afectada = tabla_afectada;
        this.id_referencia = id_referencia;
        this.detalle = detalle;
        this.id_usuario = id_usuario;
        this.tipo_operacion = tipo_operacion;
        this.fecha_hora = fecha_hora;
        this.modulo = modulo;
        this.estado_previos = estado_previos;
        this.estado_nuevo = estado_nuevo;
        this.ip_usuario = ip_usuario;
    }

    // Getters y setters
    public int getId_log() {
        return id_log;
    }

    public void setId_log(int id_log) {
        this.id_log = id_log;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla_afectada() {
        return tabla_afectada;
    }

    public void setTabla_afectada(String tabla_afectada) {
        this.tabla_afectada = tabla_afectada;
    }

    public String getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(String id_referencia) {
        this.id_referencia = id_referencia;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public TipoOperacion getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(TipoOperacion tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getEstado_previos() {
        return estado_previos;
    }

    public void setEstado_previos(String estado_previos) {
        this.estado_previos = estado_previos;
    }

    public String getEstado_nuevo() {
        return estado_nuevo;
    }

    public void setEstado_nuevo(String estado_nuevo) {
        this.estado_nuevo = estado_nuevo;
    }

    public String getIp_usuario() {
        return ip_usuario;
    }

    public void setIp_usuario(String ip_usuario) {
        this.ip_usuario = ip_usuario;
    }

    @Override
    public String toString() {
        return "Bitacora{"
                + "id_log=" + id_log
                + ", accion='" + accion + '\''
                + ", tabla_afectada='" + tabla_afectada + '\''
                + ", id_referencia='" + id_referencia + '\''
                + ", detalle='" + detalle + '\''
                + ", id_usuario=" + id_usuario
                + ", tipo_operacion=" + tipo_operacion
                + ", fecha_hora=" + fecha_hora
                + ", modulo='" + modulo + '\''
                + ", estado_previos='" + estado_previos + '\''
                + ", estado_nuevo='" + estado_nuevo + '\''
                + ", ip_usuario='" + ip_usuario + '\''
                + '}';
    }
}

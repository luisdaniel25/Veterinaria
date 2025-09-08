package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Citas {

    private int id_cita;
    private int id_mascota;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Estado estado;

    public Citas() {
    }

    public Citas(int id_mascota, LocalDate fecha, LocalTime hora, String motivo) {
        this.id_mascota = id_mascota;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = Estado.PENDIENTE;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}

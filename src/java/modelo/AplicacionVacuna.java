package modelo;

import java.time.LocalDate;

public class AplicacionVacuna {

    private int id_aplicacionVacuna;
    private int id_mascota;
    private int id_vacuna;
    private LocalDate fecha;
    private String via_aplicacion;
    private String observaciones;

    public AplicacionVacuna() {
    }

    public AplicacionVacuna(int id_aplicacionVacuna, int id_mascota, int id_vacuna, LocalDate fecha, String via_aplicacion, String observaciones) {
        this.id_aplicacionVacuna = id_aplicacionVacuna;
        this.id_mascota = id_mascota;
        this.id_vacuna = id_vacuna;
        this.fecha = fecha;
        this.via_aplicacion = via_aplicacion;
        this.observaciones = observaciones;
    }

    public int getId_aplicacionVacuna() {
        return id_aplicacionVacuna;
    }

    public void setId_aplicacionVacuna(int id_aplicacionVacuna) {
        this.id_aplicacionVacuna = id_aplicacionVacuna;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getVia_aplicacion() {
        return via_aplicacion;
    }

    public void setVia_aplicacion(String via_aplicacion) {
        this.via_aplicacion = via_aplicacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}

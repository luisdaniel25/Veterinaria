package modelo;

import java.util.Date;

public class ResetToken {

    private int id_tokens;
    private int id_usuario;
    private String token;
    private Date expiracion;

    public ResetToken() {
    }

    public ResetToken(int id_usuario, String token, Date expiracion) {
        this.id_usuario = id_usuario;
        this.token = token;
        this.expiracion = expiracion;
    }

    public int getId_tokens() {
        return id_tokens;
    }

    public void setId_tokens(int id_tokens) {
        this.id_tokens = id_tokens;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
        this.expiracion = expiracion;
    }

}

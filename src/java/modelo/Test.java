package modelo;

import util.Conexion;

public class Test {

    public static void main(String[] args) {

        Conexion test = new Conexion();

        if (test.conectarBD() != null) {
            System.out.println("Conexion Exitosa A La Base Datos");
        } else {
            System.err.println("N o Se Pudo Conectar A La Base De Datos");
        }

    }

}

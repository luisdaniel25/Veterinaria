package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectarBD() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bd_tienda_mascotas?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "2556229";
        Connection conn = null;

        try {
            Class.forName(driver); // Cargar el driver
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(" Conexión exitosa a la BD");
        } catch (ClassNotFoundException e) {
            System.out.println(" Error: No se encontró el Driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println(" Error SQL: " + e.getMessage());
        }
        return conn;
    }
}

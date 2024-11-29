package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;

    static {
        try(InputStream input = ClassLoader.getSystemResourceAsStream("db.properties")){

            Properties p = new Properties();
            if (input == null) {
                System.out.println("No se encontró el archivo db.properties en el classpath.");

            }
            p.load(input);

            String url=p.getProperty("db.url");
            String user=p.getProperty("db.user");
            String password=p.getProperty("db.password");


            connection = DriverManager.getConnection(url,user,password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void hacerTrigger(){
        String sql = "DROP TRIGGER IF EXISTS `check_cantidad_negativa`";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

            String sql1=  "CREATE TRIGGER check_cantidad_negativa\n" +
                    "BEFORE INSERT ON Produccion\n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "    IF NEW.cantidadRecolectada < 0 THEN\n" +
                    "    SIGNAL SQLSTATE '45000' \n" +
                    "    SET MESSAGE_TEXT = 'Error: La cantidad recolectada no puede ser negativa';\n" +
                    "    END IF;\n" +
                    "END\n";

            statement.execute(sql1);

            System.out.println("Disparadores creados correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

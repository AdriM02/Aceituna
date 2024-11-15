package DAO;

import conexion.DatabaseConnection;

import modelos.Almazara;
import modelos.Cuadrilla;
import modelos.Produccion;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProduccionDAO {
    private static final String FIND_ONE_QUERY = "SELECT * FROM produccion  WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM produccion";
    private static final String UPDATE_QUERY = "UPDATE produccion SET cuadrilla_id = ?, olivar_id = ?, almazara_id = ?, fecha = ?, cantidadRecolectada = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM produccion WHERE id = ?";
    private Connection connection;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ProduccionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Método para añadir una Producción
    public void addProduccion(Produccion produccion) throws SQLException {
        String query = "INSERT INTO produccion (cuadrilla_id, olivar_id, almazara_id, fecha, cantidadRecolectada) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, produccion.getCuadrilla_id());
            stmt.setInt(2, produccion.getOlivar_id());
            stmt.setInt(3, produccion.getAlmazara_id());
            stmt.setDate(4, Date.valueOf(LocalDate.parse(produccion.getFecha(),dateTimeFormatter)));
            stmt.setInt(5, produccion.getCantidadRecolectada());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo a la producción
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produccion.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //Método para mostrar una producción por id
    public Produccion getProduccionById(int id) throws SQLException {

        Produccion produccion = null;

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produccion = new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                );

            }
        }

        return produccion;
    }
    //Método para conseguir todas las producciones
    public List<Produccion> getAllProducciones() throws SQLException {
         Produccion produccion= null;
        List<Produccion> producciones  = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                produccion = new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                );
                producciones.add(produccion);

            }
        }

        return producciones;
    }

    //Método para eliminar una producción por id
    public void DeleteProduccionById(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        }

    }
    //Método para actualizar una producción
    public void UpdateProduccion(Produccion produccion) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setInt(1,produccion.getCuadrilla_id());
            stmt.setInt(2,produccion.getOlivar_id());
            stmt.setInt(3,produccion.getAlmazara_id());
            stmt.setDate(4, Date.valueOf(LocalDate.parse(produccion.getFecha(),dateTimeFormatter)));
            stmt.setInt(5,produccion.getCantidadRecolectada());
            stmt.setInt(6,produccion.getId());
            stmt.executeUpdate();
        }

    }


    // Método para obtener una producción en una fecha concreta, de una cuadrilla concreta en una almazara concreta..
    public Produccion getProduccion(String fecha, int idCuadrilla, int idAlmazara) throws SQLException {
        Produccion produccion = null;
        String query = "SELECT * FROM produccion WHERE  fecha = ? AND cuadrilla_id = ? AND almazara_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(LocalDate.parse(fecha,dateTimeFormatter)));
            stmt.setInt(2, idCuadrilla);
            stmt.setInt(3, idAlmazara);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produccion = new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                );
            }
            if (Objects.isNull(produccion)){
                System.out.println("No existe ninguna producción que coincida con los datos proporcionados.");
            }
        }

        return produccion;
    }


    // Método para obtener las producciones en una fecha concreta, en una almazara concreta.
    public List<Produccion> getProduccionesEnAlmazara(String fecha,  int idAlmazara) throws SQLException {
        List<Produccion> producciones = new ArrayList<>();
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND almazara_id = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(LocalDate.parse(fecha,dateTimeFormatter)));
            stmt.setInt(2, idAlmazara);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               producciones.add(new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                ));
            }
            if (producciones.isEmpty()){
                System.out.println("No existe ninguna producción que coincida con los datos proporcionados.");
            }
        }
        return producciones;
    }


    // Método para obtener las producciones hasta una determinada fecha, de un determinado olivar
    public List<Produccion> getProduccionesEnOlivar(String fecha,  int idOlivar) throws SQLException {
        List<Produccion> producciones = new ArrayList<>();
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND olivar_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(LocalDate.parse(fecha,dateTimeFormatter)));
            stmt.setInt(2, idOlivar);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                producciones.add(new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                ));
            }
            if (producciones.isEmpty()){
                System.out.println("No existe ninguna producción que coincida con los datos proporcionados.");
            }
        }
        return producciones;
    }


    // Método para obtener las producciones hasta una determinada fecha, de una determinada cuadrilla.
    public List<Produccion> getProduccionesPorCuadrilla(String fecha,  int idCuadrilla) throws SQLException {
        List<Produccion> producciones = new ArrayList<>();
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND  cuadrilla_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(LocalDate.parse(fecha,dateTimeFormatter)));
            stmt.setInt(2, idCuadrilla);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                producciones.add(new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getString("fecha"),
                        rs.getInt("cantidadRecolectada")

                ));
            }
            if (producciones.isEmpty()){
                System.out.println("No existe ninguna producción que coincida con los datos proporcionados.");
            }
        }
        return producciones;
    }
}

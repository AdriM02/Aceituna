package DAO;

import conexion.DatabaseConnection;
import modelos.Almazara;
import modelos.Cuadrilla;
import modelos.Olivar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OlivarDAO {
    private static final String FIND_ONE_QUERY = "SELECT * FROM olivar WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM olivar ";
    private static final String UPDATE_QUERY = "UPDATE olivar SET ubicacion = ?, hectareas = ?, produccionAnual = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM olivar WHERE id = ?";

    private Connection connection;

    public OlivarDAO() {
        connection = DatabaseConnection.getConnection();
    }
    // Método para añadir un olivar
    public void addOlivar(Olivar olivar) throws SQLException {
        String query = "INSERT INTO olivar (ubicacion, hectareas, produccionAnual) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, olivar.getUbicacion());
            stmt.setDouble(2, olivar.getHectareas());
            stmt.setDouble(3, olivar.getProduccionAnual());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo al olivar
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    olivar.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //Método para mostrar un olivar por id
    public Olivar getPById(int id) throws SQLException {

        Olivar olivar = null;

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                 olivar = new Olivar(
                        rs.getInt("id"),
                         rs.getString("ubicacion"),
                         rs.getDouble("hectareas"),
                         rs.getDouble("produccionAnual")
                );

            }
        }

        return olivar;
    }
    //Método para conseguir todas los olivares
    public List<Olivar> getAllOlivares() throws SQLException {
        Olivar olivar = null;
        List<Olivar> olivares  = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                olivar = new Olivar(
                        rs.getInt("id"),
                        rs.getString("ubicacion"),
                        rs.getDouble("hectareas"),
                        rs.getDouble("produccionAnual")
                );
                olivares.add(olivar);

            }
        }

        return olivares;
    }
    //Método para eliminar un olivar por id
    public void DeleteOlivarById(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        }

    }
    //Método para actualizar un olivar
    public void UpdateOlivar(Olivar olivar) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1,olivar.getUbicacion());
            stmt.setDouble(2,olivar.getHectareas());
            stmt.setDouble(3,olivar.getProduccionAnual());
            stmt.setInt(4,olivar.getId());
            stmt.executeUpdate();
        }

    }


    // Método para obtener las cuadrillas que trabajan en un olivar por ID.
    public List<Cuadrilla> geCuadrillasPorOlivar(int id) throws SQLException {
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String query = "SELECT c.* FROM cuadrilla c, cuadrilla_olivar co WHERE c.id = co.cuadrilla_id AND co.olivar_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cuadrillas.add(new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("supervisor_id")

                ));
            }
            if (cuadrillas.isEmpty()){
                System.out.println("Este olivar no existe o no trabajan cuadrillas en él.");
            }
        }
        return cuadrillas;
    }

}

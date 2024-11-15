package DAO;

import conexion.DatabaseConnection;
import modelos.Almazara;
import modelos.Cuadrilla;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlmazaraDAO {
    private static final String FIND_ONE_QUERY = "SELECT * FROM  almazara WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM almazara";
    private static final String UPDATE_QUERY = "UPDATE  almazara SET nombre = ?, ubicacion = ?, capacidad = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM almazara WHERE id = ?";
    private Connection connection;

    public AlmazaraDAO() {
        connection = DatabaseConnection.getConnection();
    }
    // Método para añadir una Almazara
    public void addAlmazara(Almazara almazara) throws SQLException {
        String query = "INSERT INTO almazara (nombre, ubicacion, capacidad) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, almazara.getNombre());
            stmt.setString(2, almazara.getUbicacion());
            stmt.setDouble(3, almazara.getCapacidad());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo a la almazara
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    almazara.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //Método para conseguir una almazara por id
    public Almazara getAlmazaraById(int id) throws SQLException {
        Almazara almazara = null;

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                 almazara = new Almazara (
                        rs.getInt("id"),
                         rs.getString("nombre"),
                         rs.getString("ubicacion"),
                         rs.getDouble("capacidad")
                );

            }
        }
        return almazara;
    }

    //Método para conseguir todas las almazaras
    public List<Almazara> getAllAlmazaras() {
        Almazara almazara = null;
        List<Almazara> almazaras = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                almazara = new Almazara (
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDouble("capacidad")
                );
                almazaras.add(almazara);

            }
        }catch (SQLException e){
            System.out.println("Fallo al leer las almazaras");
        }

        return almazaras;
    }

    //Método para eliminar una almazara por id
    public void deleteAlmazaraById(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        }

    }

    //Método para actualizar una almazara
    public void UpdateAlmazara(Almazara almazara) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
         stmt.setString(1,almazara.getNombre());
         stmt.setString(2,almazara.getUbicacion());
         stmt.setDouble(3,almazara.getCapacidad());
            stmt.setInt(4,almazara.getId());
            stmt.executeUpdate();
        }

    }
}

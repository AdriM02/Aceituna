package DAO;

import conexion.DatabaseConnection;
import modelos.Almazara;
import modelos.Cuadrilla;
import modelos.Olivar;
import modelos.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO {
    private static final String FIND_ONE_QUERY = "SELECT * FROM cuadrilla WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM cuadrilla";
    private static final String UPDATE_QUERY = "UPDATE cuadrilla SET nombre = ?, supervisor_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM cuadrilla WHERE id = ?";
    private Connection connection;

    public CuadrillaDAO() {
        connection = DatabaseConnection.getConnection();
    }
    // Método para añadir una Cuadrilla
    public void addCuadrilla(Cuadrilla cuadrilla) throws SQLException {
        String query = "INSERT INTO cuadrilla (nombre, supervisor_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cuadrilla.getNombre());
            stmt.setInt(2, cuadrilla.getSupervisor_id());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo a la cuadrilla
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cuadrilla.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //Método para mostrar una cuadrilla por id
    public Cuadrilla getCuadrillaById(int id) throws SQLException {

        Cuadrilla cuadrilla = null;

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                 cuadrilla = new Cuadrilla(
                        rs.getInt("id"),
                         rs.getString("nombre"),
                         rs.getInt("supervisor_id")

                );

            }
        }

        return cuadrilla;
    }

    //Método para conseguir todas las cuadrillas
    public List<Cuadrilla> getAllCuadrillas() throws SQLException {
         Cuadrilla cuadrilla= null;
        List<Cuadrilla> cuadrillas  = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuadrilla = new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("supervisor_id")

                );
                cuadrillas.add(cuadrilla);


            }
        }

        return cuadrillas;
    }

    //Método para eliminar una cuadrilla por id
    public void DeleteCuadrillaById(int id) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        }

    }

    //Método para actualizar una cuadrilla
    public void UpdateCuadrilla(Cuadrilla cuadrilla) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1,cuadrilla.getNombre());
            stmt.setInt(2,cuadrilla.getSupervisor_id());
            stmt.setInt(3,cuadrilla.getId());
            stmt.executeUpdate();
        }

    }



    // Método para obtener los trabajadores de una cuadrilla por ID.
    public List<Trabajador> getTrabajadoresPorCuadrilla(int id) throws SQLException {
        List<Trabajador> trabajadores = new ArrayList<>();
        String query = "SELECT t.* FROM trabajador t, cuadrilla c, cuadrilla_trabajador ct where t.id = ct.trabajador_id AND c.id = ct.cuadrilla_id AND c.id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trabajadores.add(new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("puesto"),
                        rs.getInt("salario")
                ));
            }
            if (trabajadores.isEmpty()){
                System.out.println("Esta cuadrilla no existe o no tiene trabajadores.");
            }
        }

        return trabajadores;
    }

    // Método para obtener los olivares en los que trabaja una cuadrilla por ID.
    public List<Olivar> getOlivaresPorCuadrilla(int id) throws SQLException {
        List<Olivar> olivares = new ArrayList<>();
        String query = "SELECT o.* FROM olivar o,cuadrilla c, cuadrilla_olivar co WHERE o.id = co.olivar_id AND c.id = co.cuadrilla_id AND c.id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                olivares.add(new Olivar(
                        rs.getInt("id"),
                        rs.getString("ubicacion"),
                        rs.getDouble("hectareas"),
                        rs.getDouble("produccionAnual")
                ));
            }
            if (olivares.isEmpty()){
                System.out.println("Esta cuadrilla no existe o no trabaja en ningún olivar");
            }
        }

        return olivares;
    }

    // Método para obtener las almazaras en las que trabaja una cuadrilla por ID.
    public List<Almazara> getAlmazarasPorCuadrilla(int id) throws SQLException {
        List<Almazara> almazaras = new ArrayList<>();
        String query = "SELECT a.* FROM almazara a, cuadrilla c, produccion p WHERE a.id=p.almazara_id AND c.id = p.cuadrilla_id AND c.id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                almazaras.add(new Almazara (
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDouble("capacidad")
                ));
            }
            if (almazaras.isEmpty()){
                System.out.println("Esta cuadrilla no existe o no trabaja en ninguna almazára");
            }
        }

        return almazaras;
    }
}

package DAO;

import conexion.DatabaseConnection;
import modelos.Cuadrilla;
import modelos.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {
    private static final String FIND_ONE_QUERY = "SELECT * FROM trabajador  WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM trabajador ";
    private static final String UPDATE_QUERY = "UPDATE trabajador SET nombre = ?, edad = ?, puesto = ?, salario = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM  trabajador WHERE id = ?";

    private Connection connection;

  public TrabajadorDAO(){
      connection = DatabaseConnection.getConnection();
  }
    // Método para añadir un trabajador
    public void addTrabajador(Trabajador trabajador) throws SQLException {
        String query = "INSERT INTO trabajador (nombre, edad, puesto, salario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trabajador.getNombre());
            stmt.setInt(2, trabajador.getEdad());
            stmt.setString(3, trabajador.getPuesto());
            stmt.setInt(4, trabajador.getSalario());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo al trabajador
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trabajador.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //Método para mostrar un trabajador por id
    public Trabajador getTrabajadorById(int id) throws SQLException {

        Trabajador trabajador = null;

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                trabajador = new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("puesto"),
                        rs.getInt("salario")

                );

            }
        }

        return trabajador;
    }
    //Método para conseguir todas los trabajadores
    public List<Trabajador> getAllTrabajadores() throws SQLException {
         Trabajador trabajador = null;
        List<Trabajador> trabajadores  = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                trabajador = new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("puesto"),
                        rs.getInt("salario")

                );
                trabajadores.add(trabajador);

            }
        }

        return trabajadores ;
    }

    //Método para eliminar un trabajador por id
    public void DeleteTrabajadorById(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        }

    }

    //Método para actualizar un trabajador
    public void UpdateTrabajador(Trabajador trabajador) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1,trabajador.getNombre());
            stmt.setInt(2,trabajador.getEdad());
            stmt.setString(3,trabajador.getPuesto());
            stmt.setInt(4,trabajador.getSalario());
            stmt.setInt(5,trabajador.getId());
            stmt.executeUpdate();
        }

    }

    // Método para obtener las cuadrillas que supervisa un trabajador por ID.
    public List<Cuadrilla> geCuadrillasPorSupervisor(int id) throws SQLException {
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String query = "SELECT c.* FROM cuadrilla c, trabajador t where c.supervisor_id = T.id and t.id = ?;";
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
                System.out.println("Este trabajador no existe o no supervisa ninguna cuadrilla");
            }
        }
        return cuadrillas;
    }
}

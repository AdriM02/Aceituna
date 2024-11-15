package modelos;

public class Cuadrilla {
    private int id;
    private String nombre;
    private int supervisor_id;

    public Cuadrilla(int id, String nombre, int supervisor_id) {
        this.id = id;
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public Cuadrilla(String nombre, int supervisor_id) {
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    @Override
    public String toString() {
        return "Cuadrilla{" +
                "id=" + id +
                ", nomre='" + nombre + '\'' +
                ", supervisor_id=" + supervisor_id +
                '}';
    }
}

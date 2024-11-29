package modelos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Cuadrilla")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id","nombre","supervisor_id"})
public class Cuadrilla {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "supervisor_id")
    private int supervisor_id;

    public Cuadrilla(int id, String nombre, int supervisor_id) {
        this.id = id;
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public Cuadrilla() {
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

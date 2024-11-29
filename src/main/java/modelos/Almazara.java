package modelos;


import javax.xml.bind.annotation.*;


@XmlRootElement(name= "Almazara")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id","nombre","ubicacion","capacidad"})
public class Almazara {
    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name="ubicacion")
    private String ubicacion;
    @XmlElement(name = "capacidad")
    private  Double capacidad;

    public Almazara(int id, String nombre, String ubicacion, Double capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad =  capacidad;
    }

    public Almazara() {
    }

    public Almazara(String nombre, String ubicacion, Double capacidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad =  capacidad;
    }

    @Override
    public String toString() {
        return "Almazara{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                '}';
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }
}

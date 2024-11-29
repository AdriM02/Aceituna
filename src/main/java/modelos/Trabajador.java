package modelos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Trabajador")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id","nombre","edad","puesto","salario"})
public class Trabajador {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "edad")
    private int edad;
    @XmlElement(name = "puesto")
    private  String puesto;
    @XmlElement(name = "salario")
    private int salario;

    public Trabajador(int id, String nombre, int edad, String puesto, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Trabajador() {
    }

    public Trabajador(String nombre, int edad, String puesto, int salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                '}';
    }
}

package marshalling;

import DAO.*;
import modelos.*;

import javax.xml.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "base")
@XmlAccessorType(XmlAccessType.FIELD)

public class Base {
    @XmlElementWrapper(name = "Almazaras")
    @XmlElement(name = "Almazara")
    private List<Almazara> almazaras = new ArrayList<>();

    @XmlElementWrapper(name = "Cuadrillas")
    @XmlElement(name = "Cuadrilla")
    private List<Cuadrilla> cuadrillas = new ArrayList<>();

    @XmlElementWrapper(name = "Olivares")
    @XmlElement(name = "Olivar")
    private List<Olivar> olivares = new ArrayList<>();

    @XmlElementWrapper(name = "Producciones")
    @XmlElement(name = "Produccion")
    private List<Produccion> producciones = new ArrayList<>();

    @XmlElementWrapper(name = "Trabajadores")
    @XmlElement(name = "Trabajador")
    private List<Trabajador> trabajadores = new ArrayList<>();


    public Base(List<Almazara> almazaras, List<Cuadrilla> cuadrillas, List<Olivar> olivares, List<Produccion> producciones, List<Trabajador> trabajadores) {
        this.almazaras = almazaras;
        this.cuadrillas = cuadrillas;
        this.olivares = olivares;
        this.producciones = producciones;
        this.trabajadores = trabajadores;
    }

    public Base() {
    }


    @Override
    public String toString() {
        return "Base{" +
                "almazaras=" + almazaras +
                ", cuadrillas=" + cuadrillas +
                ", olivares=" + olivares +
                ", producciones=" + producciones +
                ", trabajadores=" + trabajadores +
                '}';
    }

    public static Base devBase(){

        AlmazaraDAO almazaraDAO = new AlmazaraDAO();
        CuadrillaDAO cuadrillaDAO = new CuadrillaDAO();
        ProduccionDAO produccionDAO = new ProduccionDAO();
        OlivarDAO olivarDAO = new OlivarDAO();
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

        List<Almazara> almazaras1 = new ArrayList<>();
        List<Cuadrilla> cuadrillas1 = new ArrayList<>();
        List<Olivar> olivares1 = new ArrayList<>();
        List<Produccion> producciones1 = new ArrayList<>();
        List<Trabajador> trabajadores1 = new ArrayList<>();

        try{
            almazaras1.addAll(almazaraDAO.getAllAlmazaras());
            cuadrillas1.addAll(cuadrillaDAO.getAllCuadrillas());
            olivares1.addAll(olivarDAO.getAllOlivares());
            producciones1.addAll(produccionDAO.getAllProducciones());
            trabajadores1.addAll(trabajadorDAO.getAllTrabajadores());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Base(almazaras1,cuadrillas1,olivares1,producciones1,trabajadores1);
    }


}

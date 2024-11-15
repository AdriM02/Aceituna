package modelos;

public class Produccion {
    private int id;
    private int cuadrilla_id;
    private int olivar_id;
    private int almazara_id;
    private String fecha;
    private int cantidadRecolectada;


    @Override
    public String toString() {
        return "Produccion{" +
                "id=" + id +
                ", cuadrilla_id=" + cuadrilla_id +
                ", olivar_id=" + olivar_id +
                ", almazara_id=" + almazara_id +
                ", fecha='" + fecha + '\'' +
                ", cantidadRecolectada=" + cantidadRecolectada +
                '}';
    }

    public Produccion(int id, int cuadrilla_id, int olivar_id, int almazara_id, String fecha, int cantidadRecolectada) {
        this.id = id;
        this.cuadrilla_id = cuadrilla_id;
        this.olivar_id = olivar_id;
        this.almazara_id = almazara_id;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    public Produccion(int cuadrilla_id, int olivar_id, int almazara_id, String fecha, int cantidadRecolectada) {
        this.cuadrilla_id = cuadrilla_id;
        this.olivar_id = olivar_id;
        this.almazara_id = almazara_id;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    public int getAlmazara_id() {
        return almazara_id;
    }

    public void setAlmazara_id(int almazara_id) {
        this.almazara_id = almazara_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuadrilla_id() {
        return cuadrilla_id;
    }

    public void setCuadrilla_id(int cuadrilla_id) {
        this.cuadrilla_id = cuadrilla_id;
    }

    public int getOlivar_id() {
        return olivar_id;
    }

    public void setOlivar_id(int olivar_id) {
        this.olivar_id = olivar_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidadRecolectada() {
        return cantidadRecolectada;
    }

    public void setCantidadRecolectada(int cantidadRecolectada) {
        this.cantidadRecolectada = cantidadRecolectada;
    }

}

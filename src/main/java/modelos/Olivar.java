package modelos;

public class Olivar {
    private int id;
    private String ubicacion;
    private  Double hectareas;
    private Double produccionAnual;

    public Olivar(int id, String ubicacion, Double hectareas, Double produccionAnual) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public Olivar(String ubicacion, Double hectareas, Double produccionAnual) {
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getHectareas() {
        return hectareas;
    }

    public void setHectareas(Double hectareas) {
        this.hectareas = hectareas;
    }

    public Double getProduccionAnual() {
        return produccionAnual;
    }

    public void setProduccionAnual(Double produccionAnual) {
        this.produccionAnual = produccionAnual;
    }

    @Override
    public String toString() {
        return "Olivar{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", hectareas=" + hectareas +
                ", produccionAnual=" + produccionAnual +
                '}';
    }
}

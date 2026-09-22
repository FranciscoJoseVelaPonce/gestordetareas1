package todo;

public class Tarea {

    private final int id;
    private String descripcion;
    private boolean completada;
    private String prioridad; // "alta", "media" o "baja"

    public Tarea(int id, String descripcion, String prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = false;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        String estado = completada ? "[X]" : "[ ]";
        return String.format("%s #%d [%s] - %s", estado, id, prioridad.toUpperCase(), descripcion);
    }
}
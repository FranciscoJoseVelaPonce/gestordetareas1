package todo;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    private final List<Tarea> tareas = new ArrayList<>();
    private int siguienteId = 1;

    public Tarea añadirTarea(String descripcion, String prioridad) {
        Tarea tarea = new Tarea(siguienteId, descripcion, prioridad);
        siguienteId++;
        tareas.add(tarea);
        return tarea;
    }

    public List<Tarea> obtenerPendientes() {
        List<Tarea> pendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    public boolean completarTarea(int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return false;
        }
        tarea.marcarComoCompletada();
        return true;
    }

    public boolean eliminarTarea(int id) {
        Tarea tarea = buscarPorId(id);
        if (tarea == null) {
            return false;
        }
        tareas.remove(tarea);
        return true;
    }

    public boolean estaVacio() {
        return tareas.isEmpty();
    }

    private Tarea buscarPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }
}
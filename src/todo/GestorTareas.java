package todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    // Metodo para obtener pendientes
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

    public List<Tarea> obtenerPorPrioridad(String prioridad) {
        List<Tarea> filtradas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad().equalsIgnoreCase(prioridad)) {
                filtradas.add(tarea);
            }
        }
        return filtradas;
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

    // Métodos para el archivo ListadoTareas.txt
    public boolean guardarEnArchivo() {
        String nombreArchivo = "ListadoTareas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean cargarDesdeArchivo() {
        String nombreArchivo = "ListadoTareas.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
package todo;

import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final GestorTareas gestor;
    private final Scanner scanner;

    public MenuConsola(GestorTareas gestor, Scanner scanner) {
        this.gestor = gestor;
        this.scanner = scanner;
    }

    public void iniciar() {
        System.out.println("=== GESTOR DE TAREAS ===");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> añadirTarea();
                case "2" -> mostrarPendientes();
                case "3" -> completarTarea();
                case "4" -> eliminarTarea();
                case "5" -> mostrarTodas();
                case "6" -> filtrarPorPrioridad(); // Opción añadida
                case "0" -> salir = true;
                default -> System.out.println("Opción no válida. Introduce un número del 0 al 6.");
            }
        }

        System.out.println("¡Hasta luego!");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("1. Añadir tarea");
        System.out.println("2. Ver tareas pendientes");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Ver todas las tareas");
        System.out.println("6. Filtrar tareas por prioridad"); // Nueva opción en el menú
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private void añadirTarea() {
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine().trim();

        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía. Tarea no creada.");
            return;
        }

        String prioridad = leerPrioridad();

        Tarea tarea = gestor.añadirTarea(descripcion, prioridad);
        System.out.println("Tarea creada: " + tarea);
    }

    private void mostrarPendientes() {
        List<Tarea> pendientes = gestor.obtenerPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
            return;
        }

        System.out.println("Tareas pendientes:");
        for (Tarea tarea : pendientes) {
            System.out.println("  " + tarea);
        }
    }

    private void mostrarTodas() {
        List<Tarea> todas = gestor.obtenerTodas();
        if (todas.isEmpty()) {
            System.out.println("Todavía no hay tareas.");
            return;
        }

        System.out.println("Todas las tareas:");
        for (Tarea tarea : todas) {
            System.out.println("  " + tarea);
        }
    }

    // Método nuevo para consultar tareas filtradas por prioridad
    private void filtrarPorPrioridad() {
        if (gestor.estaVacio()) {
            System.out.println("Todavía no hay tareas.");
            return;
        }

        String prioridad = leerPrioridad();
        List<Tarea> filtradas = gestor.obtenerPorPrioridad(prioridad);

        if (filtradas.isEmpty()) {
            System.out.println("No hay tareas con prioridad " + prioridad + ".");
            return;
        }

        System.out.println("Tareas con prioridad " + prioridad.toUpperCase() + ":");
        for (Tarea tarea : filtradas) {
            System.out.println("  " + tarea);
        }
    }

    private void completarTarea() {
        if (gestor.estaVacio()) {
            System.out.println("Todavía no hay tareas.");
            return;
        }

        mostrarPendientes();
        int id = leerId("ID de la tarea a completar: ");
        if (id == -1) {
            return;
        }

        if (gestor.completarTarea(id)) {
            System.out.println("Tarea #" + id + " marcada como completada.");
        } else {
            System.out.println("No existe ninguna tarea con el ID " + id + ".");
        }
    }

    private void eliminarTarea() {
        if (gestor.estaVacio()) {
            System.out.println("Todavía no hay tareas.");
            return;
        }

        mostrarTodas();
        int id = leerId("ID de la tarea a eliminar: ");
        if (id == -1) {
            return;
        }

        if (gestor.eliminarTarea(id)) {
            System.out.println("Tarea #" + id + " eliminada.");
        } else {
            System.out.println("No existe ninguna tarea con el ID " + id + ".");
        }
    }

    private String leerPrioridad() {
        while (true) {
            System.out.print("Introduce la prioridad (alta, media, baja): ");
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("alta") || entrada.equals("media") || entrada.equals("baja")) {
                return entrada;
            }
            System.out.println("Prioridad no válida. Debe ser alta, media o baja.");
        }
    }

    private int leerId(String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Introducir un número.");
            return -1;
        }
    }
}
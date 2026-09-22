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
                case "1" -> anadirTarea();
                case "2" -> mostrarPendientes();
                case "3" -> completarTarea();
                case "4" -> eliminarTarea();
                case "5" -> mostrarTodas();
                case "0" -> salir = true;
                default -> System.out.println("Opcion no valida. Introduce un numero del 0 al 5.");
            }
        }

        System.out.println("Hasta luego!");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("1. Anadir tarea");
        System.out.println("2. Ver tareas pendientes");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Ver todas las tareas");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");
    }

    private void anadirTarea() {
        System.out.print("Descripcion de la tarea: ");
        String descripcion = scanner.nextLine().trim();

        if (descripcion.isEmpty()) {
            System.out.println("La descripcion no puede estar vacia. Tarea no creada.");
            return;
        }

        Tarea tarea = gestor.anadirTarea(descripcion);
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
            System.out.println("Todavia no hay tareas.");
            return;
        }

        System.out.println("Todas las tareas:");
        for (Tarea tarea : todas) {
            System.out.println("  " + tarea);
        }
    }

    private void completarTarea() {
        if (gestor.estaVacio()) {
            System.out.println("Todavia no hay tareas.");
            return;
        }

        mostrarPendientes();
        int id = leerId("ID de la tarea a completar: "); // Cambio de minúscula a mayúscula
        if (id == -1) {
            return;
        }

        if (gestor.completarTarea(id)) {
            System.out.println("Tarea #" + id + " marcada como completada.");
        } else {
            System.out.println("No existe ninguna tarea con el ID " + id + "."); // Cambio de minúscula a mayúscula
        }
    }

    private void eliminarTarea() {
        if (gestor.estaVacio()) {
            System.out.println("Todavia no hay tareas.");
            return;
        }

        mostrarTodas();
        int id = leerId("ID de la tarea a eliminar: "); // Cambio de minúscula a mayúscula
        if (id == -1) {
            return;
        }

        if (gestor.eliminarTarea(id)) {
            System.out.println("Tarea #" + id + " eliminada.");
        } else {
            System.out.println("No existe ninguna tarea con el ID " + id + "."); // Cambio de minúscula a mayúscula
        }
    }

    private int leerId(String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Introducir un numero."); // Cambio de minúscula a mayúscula
            return -1;
        }
    }
}
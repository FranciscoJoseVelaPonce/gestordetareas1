# gestor de tareas por consola

Pequeña aplicación de consola en Java para gestionar una lista de tareas.
Las tareas se guardan en memoria, es decir, se pierden al cerrar el programa.

# Qué hace

- Añadir una tarea con una descripción.
- Ver la lista de tareas pendientes.
- Marcar una tarea como completada.
- Eliminar una tarea.
- Ver todas las tareas (pendientes y completadas).
- Hemos añadido prioridades a las tareas.
- Guardar en un txt el listado de tareas.

# desde inteliji idea

1. Abrir el proyecto (`File > Open` y seleccionar la carpeta del repositorio).
2. Marcar la carpeta `src` como *Sources Root* si no lo está
   (clic derecho sobre `src` > `Mark Directory as` > `Sources Root`).
3. Abrir `src/todo/Main.java` y pulsar el botón verde de *Run*.

# Desde la terminal

```bash
javac -d out src/todo/*.java
java -cp out todo.Main
```

## Cómo se usa

Al arrancar aparece un menú; se escribe el número de la opción y se pulsa Enter:


1. Añadir tarea
2. Ver tareas pendientes
3. Marcar tarea como completada
4. Eliminar tarea
5. Ver todas las tareas
6. Filtrar tareas por prioridad
7. Guardar tareas en archivo
0. Salir

Cada tarea tiene un id numérico, que es el que se pide para completarla o
eliminarla. Las tareas se muestran con `[ ]` si están pendientes y con `[X]`
si están completadas.


La idea de esta separación es que `GestorTareas` no imprima nada por pantalla:
así la lógica queda aislada de la interfaz de consola y sería fácil reutilizarla
con otra interfaz distinta.

# Requisitos

Java 17 o superior (se usa `switch` con flechas `->`).

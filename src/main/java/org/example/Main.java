package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Declaramos constantes para el número de estudiantes y máquinas
        final int NUM_ESTUDIANTES = 5;
        final int NUM_MAQUINAS = 2;

        // El programa debe ejecutarse 20 segundos
        final long DURACION_MS = 20000;

        // CentroCopias es el monitor que gestiona las máquinas
        CentroCopias centro = new CentroCopias(NUM_MAQUINAS);

        // Guardamos estudiantes e hilos para poder pararlos y hacer el resumen final
        Estudiante[] estudiantes = new Estudiante[NUM_ESTUDIANTES];
        Thread[] hilos = new Thread[NUM_ESTUDIANTES];

        // Creamos exactamente 5 estudiantes, cada uno en su hilo
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            estudiantes[i] = new Estudiante(i, centro);

            // Aquí creamos el hilo que ejecutará al estudiante
            hilos[i] = new Thread(estudiantes[i], "Estudiante-" + i);

            // Arranca el hilo
            hilos[i].start();
        }

        // Dejamos que el sistema funcione durante 20 segundos
        Thread.sleep(DURACION_MS);

        // Cuando pasan 20 segundos, pedimos a todos los estudiantes que paren el bucle
        for (Estudiante e : estudiantes) {
            e.detener();
        }

        // Además, interrumpimos los hilos por si están bloqueados esperando una máquina
        for (Thread t : hilos) {
            t.interrupt();
        }

        // Esperamos a que todos terminen
        for (Thread t : hilos) {
            t.join();
        }

        // Resumen final con cuántas veces copió cada estudiante
        System.out.println("\n--- RESUMEN FINAL ---");
        for (Estudiante e : estudiantes) {
            System.out.println("Estudiante " + e.getIdEstudiante()
                    + " hizo copias " + e.getCopiasHechas() + " veces");
        }
    }
}

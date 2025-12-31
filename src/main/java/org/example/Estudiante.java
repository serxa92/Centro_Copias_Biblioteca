package org.example;

import java.util.Random;

public class Estudiante implements Runnable {

    private final int idEstudiante;
    private final CentroCopias centro;
    private final Random rnd = new Random();

    // Volatile lo usamos para que si el main cambia esta variable, el hilo lo vea inmediatamente
    private volatile boolean enMarcha = true;

    // Contador de cuántas veces consiguió copiar
    private int copiasHechas = 0;

    public Estudiante(int idEstudiante, CentroCopias centro) {
        this.idEstudiante = idEstudiante;
        this.centro = centro;
    }

    @Override
    public void run() {

        // Mientras esté en marcha, repite el ciclo de estudiar y copiar
        while (enMarcha) {
            try {
                // Estudia
                System.out.println("Estudiante " + idEstudiante + " está estudiando");

                // Tiempo aleatorio para que no vayan todos a la vez siempre
                Thread.sleep(300 + rnd.nextInt(700));

                // Va al centro de copias y solicita una máquina
                int maquinaAsignada = centro.solicitarMaquina(idEstudiante);

                // Hace copias
                Thread.sleep(200 + rnd.nextInt(600));
                copiasHechas++;

                // Libera la máquina
                centro.liberarMaquina(idEstudiante, maquinaAsignada);

            } catch (InterruptedException e) {
                // Si el hilo es interrumpido (por ejemplo, porque el main quiere que pare),
                // salimos del bucle y terminamos el hilo.
                if (!enMarcha) {
                    break;
                }
            } catch (RuntimeException ex) {
                // Si algo falla (por ejemplo, liberar dos veces la misma máquina),
                // lo sacamos por consola y paramos este hilo.
                System.err.println("ERROR en Estudiante " + idEstudiante + ": " + ex.getMessage());
                break;
            }
        }

        // Mensaje final antes de terminar
        System.out.println("Estudiante " + idEstudiante + " finaliza.");
    }

    // Metodo para detener el bucle del estudiante
    public void detener() {
        enMarcha = false;
    }

    // Getters para el resumen final
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getCopiasHechas() {
        return copiasHechas;
    }
}

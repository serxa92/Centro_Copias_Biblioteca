package org.example;

public class CentroCopias {

    // Array para saber si cada máquina está libre u ocupada.
    // true = libre, false = ocupada
    private final boolean[] libre;

    // Contador rápido de cuántas máquinas quedan libres
    private int libres;

    public CentroCopias(int numeroMaquinas) {
        this.libre = new boolean[numeroMaquinas];

        // Al inicio, todas las máquinas están libres
        for (int i = 0; i < numeroMaquinas; i++) {
            libre[i] = true;
        }

        this.libres = numeroMaquinas;
    }

    /**
     * Metodo para solicitar una máquina.
     * - synchronized: solo entra un estudiante a la vez.
     * - si no hay libres, el estudiante se bloquea con wait().
     * Devuelve el índice de la máquina que se le asigna.
     */
    public synchronized int solicitarMaquina(int idEstudiante) throws InterruptedException {
        System.out.println("Estudiante " + idEstudiante + " solicita máquina");

        // Mientras no haya máquinas libres, toca esperar.
        while (libres == 0) {
            System.out.println("Estudiante " + idEstudiante + " espera (no hay máquinas libres)");
            wait(); // queda bloqueado hasta que alguien notifique
        }

        // Buscamos la primera máquina libre
        for (int i = 0; i < libre.length; i++) {
            if (libre[i]) {
                libre[i] = false;
                libres--;

                System.out.println("Estudiante " + idEstudiante + " usa máquina " + (i + 1));
                return i;
            }
        }

        // Si esto pasa, hay un error en la lógica
        throw new IllegalStateException("Inconsistencia: libres=" + libres + " pero no hay máquina libre.");
    }

    /**
     * Libera una máquina y notifica a los que estén esperando.
     * synchronized: nadie libera/solicita al mismo tiempo, así no hay conflictos.
     */
    public synchronized void liberarMaquina(int idEstudiante, int maquina) {

        // Comprobaciones simples de validez
        if (maquina < 0 || maquina >= libre.length) {
            throw new IllegalArgumentException("Máquina inválida: " + maquina);
        }
        if (libre[maquina]) {
            // Si ya estaba libre, significa que alguien la liberó dos veces o algo va mal.
            throw new IllegalStateException("La máquina " + (maquina + 1) + " ya estaba libre.");
        }

        // Marcamos como libre y actualizamos contador
        libre[maquina] = true;
        libres++;

        System.out.println("Estudiante " + idEstudiante + " termina y libera máquina " + (maquina + 1));

        // Avisamos a todos los que estén esperando en solicitarMaquina().
        notifyAll();
    }
}


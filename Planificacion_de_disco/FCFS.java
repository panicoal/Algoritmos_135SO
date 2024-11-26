import java.util.LinkedList;
import java.util.Queue;

public class FCFS {

    public static void main(String[] args) {
        int[] requests = {82, 170, 43, 140, 25};
        int initialHeadPosition = 50;

        FCFS fcfs = new FCFS();
        fcfs.fcfs(requests, initialHeadPosition);
    }

    public void fcfs(int[] requests, int initialPosition) {
        int seekCount = 0;
        Queue<Integer> requestQueue = new LinkedList<>();

        // Agregar las solicitudes a la cola
        for (int request : requests) {
            requestQueue.add(request);
        }

        // Inicializar la posición actual del cabezal
        int currentPosition = initialPosition;

        System.out.println("Orden de servicio: ");
        while (!requestQueue.isEmpty()) {
            int nextRequest = requestQueue.poll();
            System.out.print(nextRequest + " ");
            seekCount += Math.abs(currentPosition - nextRequest);
            currentPosition = nextRequest;
        }

        System.out.println("\nTotal de movimientos del cabezal: " + seekCount);
    }
}

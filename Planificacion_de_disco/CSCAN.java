import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSCAN {

    public static void main(String[] args) {
        int[] requests = {82, 170, 43, 140, 25};
        int initialHeadPosition = 50;
        int diskSize = 200;

        CSCAN cscan = new CSCAN();
        cscan.cscan(requests, initialHeadPosition, diskSize);
    }

    public void cscan(int[] requests, int initialPosition, int diskSize) {
        int seekCount = 0;
        List<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        // Ordenar las solicitudes en orden ascendente
        Collections.sort(requestList);

        int currentPosition = initialPosition;
        int direction = 1; // 1 para derecha, -1 para izquierda

        while (!requestList.isEmpty()) {
            int nextRequest = -1;
            int minDiff = Integer.MAX_VALUE;

            // Encontrar la solicitud más cercana en la dirección actual
            for (int i = 0; i < requestList.size(); i++) {
                int diff = Math.abs(requestList.get(i) - currentPosition);
                if (diff < minDiff && (requestList.get(i) - currentPosition) * direction >= 0) {
                    minDiff = diff;
                    nextRequest = requestList.get(i);
                }
            }

            if (nextRequest == -1) {
                // Llegó al final del disco, cambiar dirección
                direction *= -1;
                if (direction == 1) {
                    currentPosition = 0;
                } else {
                    currentPosition = diskSize - 1;
                }
                continue;
            }

            requestList.remove(Integer.valueOf(nextRequest));
            seekCount += Math.abs(currentPosition - nextRequest);
            currentPosition = nextRequest;
        }

        System.out.println("\nTotal: " + seekCount);
    }
}
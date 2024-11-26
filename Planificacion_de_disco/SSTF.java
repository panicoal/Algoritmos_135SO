import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SSTF {

    public static void main(String[] args) {
        int[] requests = {82, 170, 43, 140, 25};
        int initialHeadPosition = 50;

        SSTF sstf = new SSTF();
        sstf.sstf(requests, initialHeadPosition);
    }

    public void sstf(int[] requests, int initialPosition) {
        int seekCount = 0;
        List<Integer> requestList = new ArrayList<>();
        for (int request : requests) {
            requestList.add(request);
        }

        int currentPosition = initialPosition;
        while (!requestList.isEmpty()) {
            int minDiff = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < requestList.size(); i++) {
                int diff = Math.abs(requestList.get(i) - currentPosition);
                if (diff < minDiff) {
                    minDiff = diff;
                    index = i;
                }
            }
            int nextRequest = requestList.remove(index);
            System.out.print(nextRequest + " ");
            seekCount += minDiff;
            currentPosition = nextRequest;
        }

        System.out.println("\nTotal de movimientos del cabezal: " + seekCount);
    }
}

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class FIFO {
    private ArrayDeque<Integer> pages;
    private Set<Integer> inMemory;
    private int capacity;

    public static void main(String[] args) {
        FIFO fifo = new FIFO(3);
        int[] referenceString = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};

        System.out.println("Paso | Página | Memoria            | Reemplazo");
        System.out.println("-----|--------|--------------------|-----------");

        int step = 1;
        for (int page : referenceString) {
            String replacedPage = fifo.accessPage(page);
            fifo.printState(step, page, replacedPage);
            step++;
        }
    }

    public FIFO(int capacity) {
        this.capacity = capacity;
        pages = new ArrayDeque<>();
        inMemory = new HashSet<>();
    }

    public String accessPage(int page) {
        String replacedPage = "Ninguno";
        if (!inMemory.contains(page)) {
            if (pages.size() == capacity) {
                int victim = pages.poll(); // Sacar la página más antigua
                inMemory.remove(victim);
                replacedPage = String.valueOf(victim);
            }
            pages.offer(page);
            inMemory.add(page);
        }
        return replacedPage;
    }

    public void printState(int step, int page, String replacedPage) {
        System.out.printf("%-5d| %-7d| %-19s| %-10s%n",
                step,
                page,
                pages.toString(),
                replacedPage);
    }
}
